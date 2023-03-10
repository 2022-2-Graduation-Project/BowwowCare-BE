package com.bowwowcare.sm.service;

import com.bowwowcare.sm.domain.care.*;
import com.bowwowcare.sm.domain.survey_result.AggressionResultRepository;
import com.bowwowcare.sm.domain.survey_result.AnxietyResultRepository;
import com.bowwowcare.sm.dto.care.AggressionCareResponseDto;
import com.bowwowcare.sm.dto.care.AnxietyCareResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CareService {

    private final AggressionResultRepository aggressionResultRepository;
    private final AggressionCareRepository aggressionCareRepository;
    private final AnxietyCareRepository anxietyCareRepository;
    private final AnxietyResultRepository anxietyResultRepository;

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
}
