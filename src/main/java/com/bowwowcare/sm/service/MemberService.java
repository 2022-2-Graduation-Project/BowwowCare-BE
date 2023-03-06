package com.bowwowcare.sm.service;

import com.bowwowcare.sm.config.security.member.MemberDetails;
import com.bowwowcare.sm.domain.theme.Theme;
import com.bowwowcare.sm.domain.theme.ThemeRepository;
import com.bowwowcare.sm.domain.user.Member;
import com.bowwowcare.sm.domain.user.MemberRepository;
import com.bowwowcare.sm.dto.user.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public UserInfoUpdateResponseDto updateUserInfo(MemberDetails memberDetails, UserInfoUpdateRequestDto userInfoUpdateRequestDto) {

        Member member = memberRepository.getOne(memberRepository.findByEmail(memberDetails.getUsername()).get().getId());

        //이름 update
        member.setUsername(userInfoUpdateRequestDto.getUsername());
        //사진 update
        if(userInfoUpdateRequestDto.getProfileImage() == null){
            member.setProfileImage(null);
        }
        else {
            member.setProfileImage(userInfoUpdateRequestDto.getProfileImage().getBytes());
        }
        //현재 테마 update
        member.setTheme(userInfoUpdateRequestDto.getTheme());
        memberRepository.save(member);


        return UserInfoUpdateResponseDto.builder()
                .id(member.getId().intValue())
                .username(member.getUsername())
                .profileImage(new String(member.getProfileImage(), StandardCharsets.UTF_8))
                .theme(member.getTheme())
                .build();
    }


    @Transactional
    public UserThemeResponseDto updateTheme(MemberDetails memberDetails, UserThemeRequestDto userThemeRequestDto) {

        Member member = memberRepository.getOne(memberRepository.findByEmail(memberDetails.getUsername()).get().getId());
        Theme theme = themeRepository.findThemeByMemberId(member.getId()).get();

        //해제하고 싶은 테마
        int wantedTheme = userThemeRequestDto.getTheme();
        //사용자가 현재 가지고 있는 테마 List
        List<Integer> themeList = findAvailableThemeList(member.getId());
        //사용할 뼈다구 개수
        int bone = userThemeRequestDto.getBones();
        //사용자가 가지고 있는 뼈다구 개수
        int userBone = member.getReward();

        String msg = "";
        if(userBone >= bone) {
            if(!themeList.contains(wantedTheme)){
                msg = "테마가 변경되었어요:)";
                updateUserAvailableThemeList(theme, wantedTheme);
                member.setTheme(wantedTheme);
                member.setReward(userBone - bone);
            }
            else {
                msg = "테마를 다시 선택해주세요!";
            }
        }
        else {
            msg = "개수가 부족해요:(";
        }
        memberRepository.save(member);

        return UserThemeResponseDto.builder()
                .message(msg)
                .theme(wantedTheme)
                .build();
    }

    private void updateUserAvailableThemeList(Theme theme, int themeNum) {

        if(themeNum == 1) { theme.setTheme1(Boolean.TRUE); }
        if(themeNum == 2) { theme.setTheme2(Boolean.TRUE); }
        if(themeNum == 3) { theme.setTheme3(Boolean.TRUE); }
        if(themeNum == 4) { theme.setTheme4(Boolean.TRUE); }
        if(themeNum == 5) { theme.setTheme5(Boolean.TRUE); }
        themeRepository.save(theme);
    }
}
