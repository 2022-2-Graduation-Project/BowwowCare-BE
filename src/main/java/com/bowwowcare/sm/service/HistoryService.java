package com.bowwowcare.sm.service;

import com.bowwowcare.sm.domain.enums.Type;
import com.bowwowcare.sm.domain.history.History;
import com.bowwowcare.sm.domain.history.HistoryRepository;
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

    private final HistoryRepository historyRepository;
    private final PetRepository petRepository;

    public List<HistoryResponseDto> saveHistory(HistoryRequestDto historyRequestDto) {

        List<History> historyList = new ArrayList<>();
        for(int i=0; i<historyRequestDto.getSituation().size(); i++){
            int x = historyRequestDto.getPetId();
            History history = historyRepository.save(
                    History.builder()
                            .type(Type.valueOf(historyRequestDto.getType().toUpperCase()))
                            .situation(historyRequestDto.getSituation().get(i).intValue())
                            .createdDate(historyRequestDto.getCreatedDate())
                            .pet(petRepository.getOne(Long.valueOf(x)))
                            .build()
            );
            historyList.add(history);
        }

        List<HistoryResponseDto> resultList = new ArrayList<>();
        for(int i=0; i<historyList.size(); i++){
            HistoryResponseDto result = HistoryResponseDto.builder()
                    .id(historyList.get(i).getId())
                    .type(historyList.get(i).getType().toString())
                    .situation(historyList.get(i).getSituation())
                    .petId(historyList.get(i).getPet().getId())
                    .build();

            resultList.add(result);
        }

        return resultList;
    }
}
