package com.bowwowcare.sm.service;

import com.bowwowcare.sm.domain.enums.Type;
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

    public List<AnxietyHistoryResponseDto> saveHistory(AnxietyHistoryRequestDto anxietyHistoryRequestDto) {

        List<AnxietyHistory> anxietyHistoryList = new ArrayList<>();
        for(int i = 0; i< anxietyHistoryRequestDto.getSituation().size(); i++){
            int x = anxietyHistoryRequestDto.getPetId();
            AnxietyHistory anxietyHistory = anxietyHistoryRepository.save(
                    AnxietyHistory.builder()
                            .type(Type.valueOf(anxietyHistoryRequestDto.getType().toUpperCase()))
                            .situation(anxietyHistoryRequestDto.getSituation().get(i).intValue())
                            .createdDate(anxietyHistoryRequestDto.getCreatedDate())
                            .pet(petRepository.getOne(Long.valueOf(x)))
                            .build()
            );
            anxietyHistoryList.add(anxietyHistory);
        }

        List<AnxietyHistoryResponseDto> resultList = new ArrayList<>();
        for(int i = 0; i< anxietyHistoryList.size(); i++){
            AnxietyHistoryResponseDto result = AnxietyHistoryResponseDto.builder()
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
