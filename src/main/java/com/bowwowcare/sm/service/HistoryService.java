package com.bowwowcare.sm.service;

import com.bowwowcare.sm.domain.care.*;
import com.bowwowcare.sm.domain.history.*;
import com.bowwowcare.sm.domain.pet.Pet;
import com.bowwowcare.sm.domain.pet.PetRepository;
import com.bowwowcare.sm.dto.history.AggressionHistoryRequestDto;
import com.bowwowcare.sm.dto.history.AggressionHistoryResponseDto;
import com.bowwowcare.sm.dto.history.AnxietyHistoryRequestDto;
import com.bowwowcare.sm.dto.history.AnxietyHistoryResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
public class HistoryService {

    private final AnxietyHistoryRepository anxietyHistoryRepository;
    private final PetRepository petRepository;
    private final AggressionHistoryRepository aggressionHistoryRepository;

    private final AggressionTypeRepository aggressionTypeRepository;
    private final AggressionCareRepository aggressionCareRepository;
    private final AnxietyCareRepository anxietyCareRepository;


    @Transactional
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

        //anxietyHistory 저장하면서 anxietyCare도 저장
        saveAnxietyCareByAnxietyHistory(anxietyHistory);

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
            if (integer == 1) { result.add(0, Boolean.TRUE); }
            if (integer == 2) { result.add(1, Boolean.TRUE); }
            if (integer == 3) { result.add(2, Boolean.TRUE); }
            if (integer == 4) { result.add(3, Boolean.TRUE); }
            if (integer == 5) { result.add(4, Boolean.TRUE); }
            if (integer == 6) { result.add(5, Boolean.TRUE); }
            if (integer == 7) { result.add(6, Boolean.TRUE); }
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


    @Transactional
    void saveAnxietyCareByAnxietyHistory(AnxietyHistory anxietyHistory) {

        Pet pet = anxietyHistory.getPet();
        //외출 전, 훈련, 외출 후로 나누어서 저장
        int[] situationList = {1,2,3};

        //각 상황별 그리고 동물 별 없다면 만들어서 저장
        //있다면 그냥 넘어감
        for (int j : situationList) {
            if(anxietyCareRepository.existsAnxietyCareBySituationAndPetId(j, pet.getId())) {
                AnxietyCare care = anxietyCareRepository.findAnxietyCareBySituationAndPetId(j, pet.getId());
                care.setModifiedAt(anxietyHistory.getCreatedDate());
                anxietyCareRepository.save(care);
            }
            else {
                AnxietyCare care = AnxietyCare.builder()
                        .count(0)
                        .missionDate(null)
                        .situation(j)
                        .isCompleted(Boolean.FALSE)
                        .sequence(0)
                        .modifiedAt(anxietyHistory.getCreatedDate())
                        .pet(pet)
                        .build();

                anxietyCareRepository.save(care);
            }
        }
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

    @Transactional
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

        saveAggressionCareByAggressionHistory(aggressionHistory);

        return AggressionHistoryResponseDto.builder()
                .id(aggressionHistory.getId())
                .aggressionType(getAggressionHistoryTypeIntegerList(aggressionHistory.getAggressionHistoryType()))
                .situation(getAggressionResponseSituationList(aggressionHistory))
                .petId(aggressionHistory.getPet().getId())
                .build();
    }


    @Transactional
    void saveAggressionCareByAggressionHistory(AggressionHistory aggressionHistory) {

        Pet pet = aggressionHistory.getPet();

        //AggressionHistory의 situation목록을 보고 해당 solution에 대해 Long타입의 List생성
        List<Integer> solutionList = getAggressionResponseSituationList(aggressionHistory);

        //동일한 pet과 solution에 대해 AggressionCare에 존재하지 않는다면 새로 생성
        for (Integer integer : solutionList) {
            if (aggressionCareRepository.existsAggressionCareBySolutionAndPetId(integer, pet.getId())) {
                AggressionCare care = aggressionCareRepository.findAggressionCareBySolutionAndPetId(integer, pet.getId());
                care.setModifiedAt(aggressionHistory.getCreatedDate());
                care.setCount(updateAggressionCareCount(makeTypeIntegerListByAggressionType(care.getAggressionType()),
                        makeTypeIntegerListByAggressionHistoryType(aggressionHistory.getAggressionHistoryType()), care.getCount()));
                care.setAggressionType(updateAggressionTypeForCare(aggressionHistory, care));
                aggressionCareRepository.save(care);
            } else {
                aggressionCareRepository.save(
                        AggressionCare.builder()
                                .count(0)
                                .missionDate(null)
                                .solution(integer)
                                .aggressionType(createAggressionTypeForCare(aggressionHistory))
                                .modifiedAt(aggressionHistory.getCreatedDate())
                                .isCompleted(Boolean.FALSE)
                                .sequence(0)
                                .pet(pet)
                                .build()
                );
            }
        }
    }

    private AggressionType createAggressionTypeForCare(AggressionHistory aggressionHistory){

        //반환할 AggressionType 생성 후 초기화
        AggressionType aggressionType = new AggressionType();
        aggressionType.setType0(Boolean.FALSE);
        aggressionType.setType1(Boolean.FALSE);
        aggressionType.setType2(Boolean.FALSE);

        //AggressionHistory로 부터 얻은 AggressionHistoryType 객체
        AggressionHistoryType aggressionHistoryType = aggressionHistory.getAggressionHistoryType();

        //aggressionHistoryType의 type0,1,2 값에 따라 aggressionType 값 update
        if(aggressionHistoryType.isType0()) { aggressionType.setType0(Boolean.TRUE); }
        if(aggressionHistoryType.isType1()) { aggressionType.setType1(Boolean.TRUE); }
        if(aggressionHistoryType.isType2()) { aggressionType.setType2(Boolean.TRUE); }

        return aggressionType;
    }

    private AggressionType updateAggressionTypeForCare(AggressionHistory aggressionHistory, AggressionCare aggressionCare){

        //기존 AggressionCare의 AggressionType 객체
        AggressionType aggressionType = aggressionCare.getAggressionType();

        //AggressionHistory의 AggressionType
        AggressionHistoryType aggressionHistoryType = aggressionHistory.getAggressionHistoryType();

        //기존 aggressionType List 초기화 후 갱신 진행
        aggressionType.setType0(Boolean.FALSE);
        aggressionType.setType1(Boolean.FALSE);
        aggressionType.setType2(Boolean.FALSE);

        if(aggressionHistoryType.isType0()) { aggressionType.setType0(Boolean.TRUE); }
        if(aggressionHistoryType.isType1()) { aggressionType.setType1(Boolean.TRUE); }
        if(aggressionHistoryType.isType2()) { aggressionType.setType2(Boolean.TRUE); }

        aggressionTypeRepository.save(aggressionType);

        return aggressionType;
    }

    private int updateAggressionCareCount(List<Integer> original, List<Integer> renewal, int originalCount) {

        int count = 0;
        //원래 2가 없을 때
        if(original.contains(2) == Boolean.FALSE) {
            //새로운 타입에 2가 있다면 count 초기화
            if(renewal.contains(2)) {
                return count;
            }
            //새로운 타입에 2가 없거나 유지상태이면 count 그대로
            return originalCount;
        }
        //원래 2가 있을 때 - 상태가 유지 혹은 좋아지면 count 유지
        else {
            return originalCount;
        }
    }

    private List<Integer> makeTypeIntegerListByAggressionType(AggressionType aggressionType) {

        List<Integer> result = new ArrayList<>();

        if(aggressionType.isType0()) { result.add(0); }
        if(aggressionType.isType1()) { result.add(1); }
        if(aggressionType.isType2()) { result.add(2); }

        return result;
    }

    private List<Integer> makeTypeIntegerListByAggressionHistoryType(AggressionHistoryType aggressionHistoryType) {

        List<Integer> result = new ArrayList<>();

        if(aggressionHistoryType.isType0()) { result.add(0); }
        if(aggressionHistoryType.isType1()) { result.add(1); }
        if(aggressionHistoryType.isType2()) { result.add(2); }

        return result;
    }
}
