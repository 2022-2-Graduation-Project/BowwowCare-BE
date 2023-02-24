package com.bowwowcare.sm.service;

import com.bowwowcare.sm.domain.history.AnxietyHistory;
import com.bowwowcare.sm.domain.history.AnxietyHistoryRepository;
import com.bowwowcare.sm.domain.pet.PetRepository;
import com.bowwowcare.sm.dto.history.AnxietyHistoryRequestDto;
import com.bowwowcare.sm.dto.history.AnxietyHistoryResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class HistoryService {

    private final AnxietyHistoryRepository anxietyHistoryRepository;
    private final PetRepository petRepository;

    public AnxietyHistoryResponseDto saveAnxietyHistory(AnxietyHistoryRequestDto anxietyHistoryRequestDto) {


        List<Boolean> situationList = findRequestSituationList(anxietyHistoryRequestDto.getSituation());

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
                .situation(getResponseSituationList(anxietyHistory))
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

    private List<Integer> getResponseSituationList(AnxietyHistory anxietyHistory) {

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
}
