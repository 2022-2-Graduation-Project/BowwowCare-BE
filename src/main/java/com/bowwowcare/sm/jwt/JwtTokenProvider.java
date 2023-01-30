package com.bowwowcare.sm.jwt;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Setter
@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    @Value("${spring.jwt.secretKey}")
    private String secretKey;

    /** 토큰 지속 시간 mill * sec * min * hour * day */
    private long tokenValidTime = 1000L * 60 * 30; // 30분
    private long refreshTokenValidTime = 1000L * 60 * 60 * 24 * 7; // 7일

    /** Security UserDetailsService */
    private final UserDetailsService userDetailsService;
}
