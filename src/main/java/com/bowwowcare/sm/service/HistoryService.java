package com.bowwowcare.sm.service;

import com.bowwowcare.sm.domain.history.*;
import com.bowwowcare.sm.domain.pet.PetRepository;
import com.bowwowcare.sm.dto.history.AggressionHistoryRequestDto;
import com.bowwowcare.sm.dto.history.AggressionHistoryResponseDto;
import com.bowwowcare.sm.dto.history.AnxietyHistoryRequestDto;
import com.bowwowcare.sm.dto.history.AnxietyHistoryResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
public class HistoryService {

    private final AnxietyHistoryRepository anxietyHistoryRepository;
    private final PetRepository petRepository;
    private final AggressionHistoryRepository aggressionHistoryRepository;


    public AnxietyHistoryResponseDto saveAnxietyHistory(AnxietyHistoryRequestDto anxietyHistoryRequestDto) {


        List<Integer> list = anxietyHistoryRequestDto.getSituation();
        Collections.sort(list);
        List<Boolean> situationList = findRequestSituationList(list);

        int x = anxietyHistoryRequestDto.getPetId();
        AnxietyHistory anxietyHistory = AnxietyHistory.builder()
                .situation1(situationList.get(0))
                .situation2(situationList.get(1))
                .situation3(situationList.get(2))
                .situation4(situationList.get(3))
                .situation5(situationList.get(4))
                .situation6(situationList.get(5))
                .situation7(situationList.get(6))
                .createdDate(anxietyHistoryRequestDto.getCreatedDate())
                .pet(petRepository.getOne((long) x))
                .build();
        anxietyHistoryRepository.save(anxietyHistory);

        return AnxietyHistoryResponseDto.builder()
                .id(anxietyHistory.getId())
                .situation(getAnxietyResponseSituationList(anxietyHistory))
                .petId(anxietyHistory.getPet().getId())
                .build();
    }


    private List<Boolean> findRequestSituationList(List<Integer> list) {

        List<Boolean> result = new ArrayList<>();
        for(int i=0; i<7; i++){
            result.add(Boolean.FALSE);
        }
        for (Integer integer : list) {
            if (integer == 1) {
                result.add(0, Boolean.TRUE);
            }
            if (integer == 2) {
                result.add(1, Boolean.TRUE);
            }
            if (integer == 3) {
                result.add(2, Boolean.TRUE);
            }
            if (integer == 4) {
                result.add(3, Boolean.TRUE);
            }
            if (integer == 5) {
                result.add(4, Boolean.TRUE);
            }
            if (integer == 6) {
                result.add(5, Boolean.TRUE);
            }
            if (integer == 7) {
                result.add(6, Boolean.TRUE);
            }
        }

        return result;
    }

    /**
     * responseDto 에 실을 situation 리스트 만드는 메서드
     * 분리불안
     */
    private List<Integer> getAnxietyResponseSituationList(AnxietyHistory anxietyHistory) {

        List<Integer> result = new ArrayList<>();

        if(anxietyHistory.isSituation1()) { result.add(1); }
        if(anxietyHistory.isSituation2()) { result.add(2); }
        if(anxietyHistory.isSituation3()) { result.add(3); }
        if(anxietyHistory.isSituation4()) { result.add(4); }
        if(anxietyHistory.isSituation5()) { result.add(5); }
        if(anxietyHistory.isSituation6()) { result.add(6); }
        if(anxietyHistory.isSituation7()) { result.add(7); }

        return result;
    }

    /**
     * responseDto 에 실을 situation 리스트 만드는 메서드
     * 공격행동
     */
    private List<Integer> getAggressionResponseSituationList(AggressionHistory aggressionHistory) {

        List<Integer> result = new ArrayList<>();

        if(aggressionHistory.isSituation1()) { result.add(1); }
        if(aggressionHistory.isSituation2()) { result.add(2); }
        if(aggressionHistory.isSituation3()) { result.add(3); }
        if(aggressionHistory.isSituation4()) { result.add(4); }
        if(aggressionHistory.isSituation5()) { result.add(5); }
        if(aggressionHistory.isSituation6()) { result.add(6); }
        if(aggressionHistory.isSituation7()) { result.add(7); }

        return result;
    }


    private AggressionHistoryType getAggressionHistoryTypeByRequestDto(List<Integer> list) {

        Collections.sort(list);
        AggressionHistoryType aggressionHistoryType = new AggressionHistoryType();

        for (Integer integer : list) {
            if (integer == 0) { aggressionHistoryType.setType0(Boolean.TRUE); }
            else if (integer == 1) { aggressionHistoryType.setType1(Boolean.TRUE); }
            else if (integer == 2) { aggressionHistoryType.setType2(Boolean.TRUE); }
        }

        return aggressionHistoryType;
    }

    private List<Integer> getAggressionHistoryTypeIntegerList(AggressionHistoryType aggressionHistoryType) {

        List<Integer> result = new ArrayList<>();

        if(aggressionHistoryType.isType0()) { result.add(0); }
        if(aggressionHistoryType.isType1()) { result.add(1); }
        if(aggressionHistoryType.isType2()) { result.add(2); }

        return result;
    }

    public AggressionHistoryResponseDto saveAggressionHistory(AggressionHistoryRequestDto aggressionHistoryRequestDto) {

        List<Integer> list = aggressionHistoryRequestDto.getSituation();
        Collections.sort(list);
        List<Boolean> situationList = findRequestSituationList(list);

        int x = aggressionHistoryRequestDto.getPetId();
        AggressionHistory aggressionHistory = AggressionHistory.builder()
                .aggressionHistoryType(getAggressionHistoryTypeByRequestDto(aggressionHistoryRequestDto.getAggressionType()))
                .situation1(situationList.get(0))
                .situation2(situationList.get(1))
                .situation3(situationList.get(2))
                .situation4(situationList.get(3))
                .situation5(situationList.get(4))
                .situation6(situationList.get(5))
                .situation7(situationList.get(6))
                .createdDate(aggressionHistoryRequestDto.getCreatedDate())
                .pet(petRepository.getOne((long) x))
                .build();
        aggressionHistoryRepository.save(aggressionHistory);

        return AggressionHistoryResponseDto.builder()
                .id(aggressionHistory.getId())
                .aggressionType(getAggressionHistoryTypeIntegerList(aggressionHistory.getAggressionHistoryType()))
                .situation(getAggressionResponseSituationList(aggressionHistory))
                .petId(aggressionHistory.getPet().getId())
                .build();
    }
}
