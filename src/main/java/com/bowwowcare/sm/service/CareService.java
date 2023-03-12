package com.bowwowcare.sm.service;

import com.bowwowcare.sm.config.security.member.MemberDetails;
import com.bowwowcare.sm.domain.care.*;
import com.bowwowcare.sm.domain.survey_result.AggressionResultRepository;
import com.bowwowcare.sm.domain.survey_result.AnxietyResultRepository;
import com.bowwowcare.sm.domain.user.Member;
import com.bowwowcare.sm.domain.user.MemberRepository;
import com.bowwowcare.sm.dto.care.AggressionCareResponseDto;
import com.bowwowcare.sm.dto.care.AnxietyCareResponseDto;
import com.bowwowcare.sm.dto.care.CareMissionRequestDto;
import com.bowwowcare.sm.dto.care.CareMissionResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CareService {

    private final AggressionResultRepository aggressionResultRepository;
    private final AggressionCareRepository aggressionCareRepository;
    private final AnxietyCareRepository anxietyCareRepository;
    private final AnxietyResultRepository anxietyResultRepository;
    private final MemberRepository memberRepository;

    public List<AggressionCareResponseDto> findAggressionCareList(int petId) {

        //반환할 List
        List<AggressionCareResponseDto> result = new ArrayList<>();

        List<AggressionCare> aggressionCareList = aggressionCareRepository.findAllByPetId((long)petId);
        for (AggressionCare care : aggressionCareList) {
            AggressionCareResponseDto aggressionCareResponseDto = AggressionCareResponseDto.builder()
                    .id(care.getId().intValue())
                    .count(care.getCount())
                    .missionDate(care.getMissionDate())
                    .aggressionType(getAggressionTypeListByAggressionCare(care))
                    .solution(new String(aggressionResultRepository.getOne(Long.valueOf(care.getSolution())).getSolution(), StandardCharsets.UTF_8))
                    .modifiedAt(care.getModifiedAt())
                    .isCompleted(care.isCompleted())
                    .build();
            result.add(aggressionCareResponseDto);
        }

        return result;
    }

    private List<Integer> getAggressionTypeListByAggressionCare(AggressionCare aggressionCare) {

        AggressionType aggressionType = aggressionCare.getAggressionType();

        List<Integer> result = new ArrayList<>();
        if(aggressionType.isType0()) { result.add(0); }
        if(aggressionType.isType1()) { result.add(1); }
        if(aggressionType.isType2()) { result.add(2); }

        return result;
    }

    //특정 pet에 대한 care를 다 찾아서 주는 것 - 분리불안
    public List<AnxietyCareResponseDto> findAnxietyCareList(int petId) {

        //반환할 List
        List<AnxietyCareResponseDto> result = new ArrayList<>();

        /*
        situation
        - 1 : 외출하기 전
        - 2 : 훈련
        - 3 : 외출 후
        */
        List<AnxietyCare> anxietyCareList = anxietyCareRepository.findAllByPetId((long) petId);
        for(AnxietyCare care : anxietyCareList) {
            AnxietyCareResponseDto anxietyCareResponseDto = AnxietyCareResponseDto.builder()
                    .id(care.getId().intValue())
                    .count(care.getCount())
                    .missionDate(care.getMissionDate())
                    .situation(new String(anxietyResultRepository.getOne((long) care.getSituation()).getSituation(), StandardCharsets.UTF_8))
                    .solution(getSolutionListByAnxietyCare(care))
                    .modifiedAt(care.getModifiedAt())
                    .isCompleted(care.isCompleted())
                    .build();
            result.add(anxietyCareResponseDto);
        }

        return result;
    }

    private List<String> getSolutionListByAnxietyCare(AnxietyCare anxietyCare) {

        List<String> result = new ArrayList<>();
        Long situation = (long) anxietyCare.getSituation();

        if(anxietyResultRepository.getOne(situation).getSolution1() != null) {
            result.add(new String(anxietyResultRepository.getOne(situation).getSolution1(), StandardCharsets.UTF_8));
        }
        if(anxietyResultRepository.getOne(situation).getSolution2() != null) {
            result.add(new String(anxietyResultRepository.getOne(situation).getSolution2(), StandardCharsets.UTF_8));
        }
        if(anxietyResultRepository.getOne(situation).getSolution3() != null) {
            result.add(new String(anxietyResultRepository.getOne(situation).getSolution3(), StandardCharsets.UTF_8));
        }

        return result;
    }


    //미션 실천에 따른 missionDate 갱신과 count 증가
    public CareMissionResponseDto CalCareMissionCount(CareMissionRequestDto careMissionRequestDto, String type, MemberDetails memberDetails) {

        Member member = memberRepository.getOne(memberRepository.findByEmail(memberDetails.getUsername()).get().getId());
        CareMissionResponseDto careMissionResponseDto = new CareMissionResponseDto();
        String msg = null;

        if(type.equals("aggression")) {
            AggressionCare aggressionCare = aggressionCareRepository.getOne((long) careMissionRequestDto.getId());

            //7일 연속 실천 여부 체크 -> 7일 연속 달성 시 리워드 지급
            if(aggressionCare.getMissionDate() == null) {
                aggressionCare.setSequence(0);
            }
            else {
                long sequence = ChronoUnit.DAYS.between(careMissionRequestDto.getMissionDate(), aggressionCare.getMissionDate());
                if(((int) sequence * -1) == 1) {
                    aggressionCare.setSequence(aggressionCare.getSequence() + ((int) sequence * -1));
                    if(aggressionCare.getSequence() == 6) {
                        msg = "리워드가 지급되었어요! 확인하러 가보세요:)";
                        aggressionCare.setSequence(0);
                        member.setReward(member.getReward() + 5);
                        memberRepository.save(member);
                    }
                }
                else {
                    aggressionCare.setSequence(0);
                }

            }

            aggressionCare.setMissionDate(careMissionRequestDto.getMissionDate());
            aggressionCare.setCount(aggressionCare.getCount() + 1);

            //총 30회 실천했을 때 리워드 지급
            if(aggressionCare.getCount() == 30) {
                aggressionCare.setCompleted(Boolean.TRUE);
                msg = "리워드가 지급되었어요! 확인하러 가보세요:)";
                member.setReward(member.getReward() + 10);
                memberRepository.save(member);
            }
            aggressionCareRepository.save(aggressionCare);

            careMissionResponseDto = CareMissionResponseDto.builder()
                    .id(aggressionCare.getId().intValue())
                    .count(aggressionCare.getCount())
                    .missionDate(aggressionCare.getMissionDate())
                    .isCompleted(aggressionCare.isCompleted())
                    .message(msg)
                    .build();
        }
        else if(type.equals("anxiety")) {
            AnxietyCare anxietyCare = anxietyCareRepository.getOne((long) careMissionRequestDto.getId());

            //7일 연속 실천 여부 체크 -> 7일 연속 달성 시 리워드 지급
            if(anxietyCare.getMissionDate() == null) {
                anxietyCare.setSequence(0);
            }
            else {
                long sequence = ChronoUnit.DAYS.between(careMissionRequestDto.getMissionDate(), anxietyCare.getMissionDate());
                if(((int) sequence * -1) == 1) {
                    anxietyCare.setSequence(anxietyCare.getSequence() + ((int) sequence * -1));
                    if(anxietyCare.getSequence() == 6) {
                        msg = "리워드가 지급되었어요! 확인하러 가보세요:)";
                        anxietyCare.setSequence(0);
                        member.setReward(member.getReward() + 5);
                        memberRepository.save(member);
                    }
                }
                else {
                    anxietyCare.setSequence(0);
                }

            }

            anxietyCare.setMissionDate(careMissionRequestDto.getMissionDate());
            anxietyCare.setCount(anxietyCare.getCount() + 1);

            //총 30회 실천했을 때 리워드 지급
            if(anxietyCare.getCount() == 30) {
                anxietyCare.setCompleted(Boolean.TRUE);
                msg = "리워드가 지급되었어요! 확인하러 가보세요:)";
                member.setReward(member.getReward() + 10);
                memberRepository.save(member);
            }
            anxietyCareRepository.save(anxietyCare);

            careMissionResponseDto = CareMissionResponseDto.builder()
                    .id(anxietyCare.getId().intValue())
                    .count(anxietyCare.getCount())
                    .missionDate(anxietyCare.getMissionDate())
                    .isCompleted(anxietyCare.isCompleted())
                    .message(msg)
                    .build();
        }

        return careMissionResponseDto;
    }
}
