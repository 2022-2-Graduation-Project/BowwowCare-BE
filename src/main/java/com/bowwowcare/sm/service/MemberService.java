package com.bowwowcare.sm.service;

import com.bowwowcare.sm.config.security.member.MemberDetails;
import com.bowwowcare.sm.domain.theme.Theme;
import com.bowwowcare.sm.domain.theme.ThemeRepository;
import com.bowwowcare.sm.domain.user.Member;
import com.bowwowcare.sm.domain.user.MemberRepository;
import com.bowwowcare.sm.dto.user.UserInfoResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final ThemeRepository themeRepository;

    public UserInfoResponseDto findUserInfo(MemberDetails memberDetails) {

        Member member = memberRepository.findByEmail(memberDetails.getUsername()).get();

        //profileImage
        String img = "";
        if(member.getProfileImage() == null){
            img = null;
        }
        else {
            img = new String(member.getProfileImage(), StandardCharsets.UTF_8);
        }

        //availableTheme List
        List<Integer> themeList = findAvailableThemeList(member.getId());


        return UserInfoResponseDto.builder()
                .id(member.getId())
                .email(member.getEmail())
                .username(member.getUsername())
                .theme(member.getTheme())
                .reward(member.getReward())
                .availableTheme(themeList)
                .profileImage(img)
                .build();
    }

    private List<Integer> findAvailableThemeList(Long id) {

        Theme theme = themeRepository.findThemeByMemberId(id).get();
        List<Integer> result = new ArrayList<>();

        result.add(0);
        if(theme.isTheme1()) { result.add(1); }
        if(theme.isTheme2()) { result.add(2); }
        if(theme.isTheme3()) { result.add(3); }
        if(theme.isTheme4()) { result.add(4); }
        if(theme.isTheme5()) { result.add(5); }

        return result;
    }
}
