package com.bowwowcare.sm.service;

import com.bowwowcare.sm.domain.theme.ThemeRepository;
import com.bowwowcare.sm.domain.user.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final ThemeRepository themeRepository;
}
