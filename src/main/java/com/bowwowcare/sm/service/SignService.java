package com.bowwowcare.sm.service;

import com.bowwowcare.sm.advice.exception.InvalidRefreshTokenException;
import com.bowwowcare.sm.advice.exception.LoginFailureException;
import com.bowwowcare.sm.advice.exception.UserEmailAlreadyExistsException;
import com.bowwowcare.sm.advice.exception.UserNotFoundException;
import com.bowwowcare.sm.domain.user.User;
import com.bowwowcare.sm.domain.user.UserRepository;
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

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SignService {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * dto로 들어온 값을 통해 회원가입 진행
     * @param userRegisterRequestDto
     * @return
     */
    @Transactional
    public UserRegisterResponseDto registerMember(UserRegisterRequestDto userRegisterRequestDto) {
        validateDuplicated(userRegisterRequestDto.getEmail());
        User user = userRepository.save(
                User.builder()
                        .email(userRegisterRequestDto.getEmail())
                        .password(passwordEncoder.encode(userRegisterRequestDto.getPassword()))
                        //.provider(null)
                        .username(userRegisterRequestDto.getUsername())
                        .build());

        return UserRegisterResponseDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .build();
    }

    /**
     * Unique한 값을 가져야함 + 중복된 email을 가질 경우를 검증
     * @param email
     */
    public void validateDuplicated(String email) {
        if (userRepository.findByEmail(email).isPresent())
            throw new UserEmailAlreadyExistsException();
    }

    /**
     * 로그인 구현
     * @param userLoginRequestDto
     * @return 토큰을 만들어 반환(프론트에 토큰을 넘겨주어 프론트에서 헤더에 담는 방식)
     */

    @Transactional
    public UserLoginResponseDto loginMember(UserLoginRequestDto userLoginRequestDto) {
        User user = userRepository.findByEmail(userLoginRequestDto.getEmail()).orElseThrow(LoginFailureException::new);
        if (!passwordEncoder.matches(userLoginRequestDto.getPassword(), user.getPassword()))
            throw new LoginFailureException();
        user.updateRefreshToken(jwtTokenProvider.createRefreshToken());
        return new UserLoginResponseDto(user.getId(), jwtTokenProvider.createToken(userLoginRequestDto.getEmail()), user.getRefreshToken());
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

        User user = findMemberByToken(requestDto);

        if (!user.getRefreshToken().equals(requestDto.getRefreshToken()))
            throw new InvalidRefreshTokenException();

        String accessToken = jwtTokenProvider.createToken(user.getEmail());
        String refreshToken = jwtTokenProvider.createRefreshToken();
        user.updateRefreshToken(refreshToken);
        return new TokenResponseDto(accessToken, refreshToken);
    }

    public User findMemberByToken(TokenRequestDto requestDto) {
        Authentication auth = jwtTokenProvider.getAuthentication(requestDto.getAccessToken());
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        String username = userDetails.getUsername();
        return userRepository.findByEmail(username).orElseThrow(UserNotFoundException::new);
    }
}
