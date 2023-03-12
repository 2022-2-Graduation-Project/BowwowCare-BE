package com.bowwowcare.sm.service;

import com.bowwowcare.sm.advice.exception.InvalidRefreshTokenException;
import com.bowwowcare.sm.advice.exception.LoginFailureException;
import com.bowwowcare.sm.advice.exception.UserEmailAlreadyExistsException;
import com.bowwowcare.sm.advice.exception.UserNotFoundException;
import com.bowwowcare.sm.domain.enums.Role;
import com.bowwowcare.sm.domain.user.Theme;
import com.bowwowcare.sm.domain.user.Member;
import com.bowwowcare.sm.domain.user.MemberRepository;
import com.bowwowcare.sm.dto.token.TokenRequestDto;
import com.bowwowcare.sm.dto.token.TokenResponseDto;
import com.bowwowcare.sm.dto.user.UserLoginRequestDto;
import com.bowwowcare.sm.dto.user.UserLoginResponseDto;
import com.bowwowcare.sm.dto.user.UserRegisterRequestDto;
import com.bowwowcare.sm.dto.user.UserRegisterResponseDto;
import com.bowwowcare.sm.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SignService {

    private final JwtTokenProvider jwtTokenProvider;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final ThemeRepository themeRepository;

    /**
     * dto로 들어온 값을 통해 회원가입 진행
     * @param userRegisterRequestDto
     * @return
     */
    @Transactional
    public UserRegisterResponseDto registerMember(UserRegisterRequestDto userRegisterRequestDto) {
        validateDuplicated(userRegisterRequestDto.getEmail());
        Member member = memberRepository.save(
                Member.builder()
                        .email(userRegisterRequestDto.getEmail())
                        .password(passwordEncoder.encode(userRegisterRequestDto.getPassword()))
                        .roles(Collections.singletonList(Role.ROLE_USER))
                        .username(userRegisterRequestDto.getUsername())
                        .profileImage(null)
                        .reward(10)
                        .theme(0)
                        .build());

        Theme theme = themeRepository.save(
                Theme.builder()
                        .theme1(Boolean.FALSE)
                        .theme2(Boolean.FALSE)
                        .theme3(Boolean.FALSE)
                        .theme4(Boolean.FALSE)
                        .theme5(Boolean.FALSE)
                        .member(member)
                        .build()
        );

        return UserRegisterResponseDto.builder()
                .id(member.getId())
                .email(member.getEmail())
                .build();
    }

    /**
     * Unique한 값을 가져야함 + 중복된 email을 가질 경우를 검증
     * @param email
     */
    public void validateDuplicated(String email) {
        if (memberRepository.findByEmail(email).isPresent())
            throw new UserEmailAlreadyExistsException();
    }

    /**
     * 로그인 구현
     * @param userLoginRequestDto
     * @return 토큰을 만들어 반환(프론트에 토큰을 넘겨주어 프론트에서 헤더에 담는 방식)
     */

    @Transactional
    public UserLoginResponseDto loginMember(UserLoginRequestDto userLoginRequestDto) {
        Member member = memberRepository.findByEmail(userLoginRequestDto.getEmail()).orElseThrow(LoginFailureException::new);
        if (!passwordEncoder.matches(userLoginRequestDto.getPassword(), member.getPassword()))
            throw new LoginFailureException();
        member.updateRefreshToken(jwtTokenProvider.createRefreshToken());
        return new UserLoginResponseDto(member.getId(), jwtTokenProvider.createToken(userLoginRequestDto.getEmail()), member.getRefreshToken());
    }

    /**
     * 토큰 재발행
     * @param requestDto
     * @return
     */
    @Transactional
    public TokenResponseDto reIssue(TokenRequestDto requestDto) {
        if (!jwtTokenProvider.validateTokenExpiration(requestDto.getRefreshToken()))
            throw new InvalidRefreshTokenException();

        Member member = findMemberByToken(requestDto);

        if (!member.getRefreshToken().equals(requestDto.getRefreshToken()))
            throw new InvalidRefreshTokenException();

        String accessToken = jwtTokenProvider.createToken(member.getEmail());
        String refreshToken = jwtTokenProvider.createRefreshToken();
        member.updateRefreshToken(refreshToken);
        return new TokenResponseDto(accessToken, refreshToken);
    }

    public Member findMemberByToken(TokenRequestDto requestDto) {
        Authentication auth = jwtTokenProvider.getAuthentication(requestDto.getAccessToken());
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        String username = userDetails.getUsername();
        return memberRepository.findByEmail(username).orElseThrow(UserNotFoundException::new);
    }
}
