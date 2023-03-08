package com.bowwowcare.sm.service;

import com.bowwowcare.sm.domain.care.AggressionCare;
import com.bowwowcare.sm.domain.care.AggressionCareRepository;
import com.bowwowcare.sm.domain.care.AggressionType;
import com.bowwowcare.sm.domain.survey_result.AggressionResultRepository;
import com.bowwowcare.sm.dto.care.AggressionCareResponseDto;
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
}
