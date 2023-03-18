package com.bowwowcare.sm.service;

import com.bowwowcare.sm.config.security.member.MemberDetails;
import com.bowwowcare.sm.domain.user.Theme;
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
                .theme(member.getCurrentTheme())
                .reward(member.getReward())
                .availableTheme(themeList)
                .profileImage(img)
                .build();
    }

    private List<Integer> findAvailableThemeList(Long id) {

        Theme theme = memberRepository.getOne(id).getTheme();
        List<Integer> result = new ArrayList<>();

        result.add(0);
        if(theme.isTheme1()) { result.add(1); }
        if(theme.isTheme2()) { result.add(2); }
        if(theme.isTheme3()) { result.add(3); }
        if(theme.isTheme4()) { result.add(4); }
        if(theme.isTheme5()) { result.add(5); }
        if(theme.isTheme6()) { result.add(6); }
        if(theme.isTheme7()) { result.add(7); }
        if(theme.isTheme8()) { result.add(8); }
        if(theme.isTheme9()) { result.add(9); }
        if(theme.isTheme10()) { result.add(10); }

        return result;
    }

    @Transactional
    // New update 메서드 - theme
    public UserInfoUpdateResponseDto updateUserTheme(MemberDetails memberDetails, UserThemeUpdateRequestDto userThemeUpdateRequestDto) {

        Member member = memberRepository.getOne(memberRepository.findByEmail(memberDetails.getUsername()).get().getId());

        //현재 테마 update
        member.setCurrentTheme(userThemeUpdateRequestDto.getTheme());
        memberRepository.save(member);


        return UserInfoUpdateResponseDto.builder()
                .id(member.getId().intValue())
                .username(member.getUsername())
                .profileImage(new String(member.getProfileImage(), StandardCharsets.UTF_8))
                .theme(member.getCurrentTheme())
                .build();
    }


    // New update 메서드 - image
    public UserInfoUpdateResponseDto updateUserImage(MemberDetails memberDetails, UserImageUpdateRequestDto userImageUpdateRequestDto) {

        Member member = memberRepository.getOne(memberRepository.findByEmail(memberDetails.getUsername()).get().getId());

        //사진 update
        if(userImageUpdateRequestDto.getProfileImage() == null){
            member.setProfileImage(null);
        }
        else {
            member.setProfileImage(userImageUpdateRequestDto.getProfileImage().getBytes());
        }
        memberRepository.save(member);


        return UserInfoUpdateResponseDto.builder()
                .id(member.getId().intValue())
                .username(member.getUsername())
                .profileImage(new String(member.getProfileImage(), StandardCharsets.UTF_8))
                .theme(member.getCurrentTheme())
                .build();
    }


    @Transactional
    public UserThemeResponseDto updateTheme(MemberDetails memberDetails, UserThemeRequestDto userThemeRequestDto) {

        Member member = memberRepository.getOne(memberRepository.findByEmail(memberDetails.getUsername()).get().getId());
        Theme theme = memberRepository.getOne(member.getId()).getTheme();

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
                member.setTheme(updateUserAvailableThemeList(theme, wantedTheme));
                member.setCurrentTheme(wantedTheme);
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

    private Theme updateUserAvailableThemeList(Theme theme, int themeNum) {

        if(themeNum == 1) { theme.setTheme1(Boolean.TRUE); }
        if(themeNum == 2) { theme.setTheme2(Boolean.TRUE); }
        if(themeNum == 3) { theme.setTheme3(Boolean.TRUE); }
        if(themeNum == 4) { theme.setTheme4(Boolean.TRUE); }
        if(themeNum == 5) { theme.setTheme5(Boolean.TRUE); }
        if(themeNum == 6) { theme.setTheme6(Boolean.TRUE); }
        if(themeNum == 7) { theme.setTheme7(Boolean.TRUE); }
        if(themeNum == 8) { theme.setTheme8(Boolean.TRUE); }
        if(themeNum == 9) { theme.setTheme9(Boolean.TRUE); }
        if(themeNum == 10) { theme.setTheme10(Boolean.TRUE); }
        return theme;
    }
}
