package com.bowwowcare.sm.service;

import com.bowwowcare.sm.domain.enums.Type;
import com.bowwowcare.sm.domain.history.AnxietyHistory;
import com.bowwowcare.sm.domain.history.AnxietyHistoryRepository;
import com.bowwowcare.sm.domain.pet.PetRepository;
import com.bowwowcare.sm.dto.history.HistoryRequestDto;
import com.bowwowcare.sm.dto.history.HistoryResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class HistoryService {

    private final AnxietyHistoryRepository anxietyHistoryRepository;
    private final PetRepository petRepository;

    public List<HistoryResponseDto> saveHistory(HistoryRequestDto historyRequestDto) {

        List<AnxietyHistory> anxietyHistoryList = new ArrayList<>();
        for(int i=0; i<historyRequestDto.getSituation().size(); i++){
            int x = historyRequestDto.getPetId();
            AnxietyHistory anxietyHistory = anxietyHistoryRepository.save(
                    AnxietyHistory.builder()
                            .type(Type.valueOf(historyRequestDto.getType().toUpperCase()))
                            .situation(historyRequestDto.getSituation().get(i).intValue())
                            .createdDate(historyRequestDto.getCreatedDate())
                            .pet(petRepository.getOne(Long.valueOf(x)))
                            .build()
            );
            anxietyHistoryList.add(anxietyHistory);
        }

        List<HistoryResponseDto> resultList = new ArrayList<>();
        for(int i = 0; i< anxietyHistoryList.size(); i++){
            HistoryResponseDto result = HistoryResponseDto.builder()
                    .id(anxietyHistoryList.get(i).getId())
                    .type(anxietyHistoryList.get(i).getType().toString())
                    .situation(anxietyHistoryList.get(i).getSituation())
                    .petId(anxietyHistoryList.get(i).getPet().getId())
                    .build();

            resultList.add(result);
        }

        return resultList;
    }
}
