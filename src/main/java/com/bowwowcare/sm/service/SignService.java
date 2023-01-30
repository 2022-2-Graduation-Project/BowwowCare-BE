package com.bowwowcare.sm.service;

import com.bowwowcare.sm.domain.user.User;
import com.bowwowcare.sm.domain.user.UserRepository;
import com.bowwowcare.sm.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
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
                        .nickname(userRegisterRequestDto.getNickname())
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

}
