package com.bowwowcare.sm.service;

import com.bowwowcare.sm.domain.analyze.Analyzation;
import com.bowwowcare.sm.domain.analyze.AnalyzationRepository;
import com.bowwowcare.sm.dto.analyze.AnalyzeRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AnalyzeService {

    private final AnalyzationRepository analyzationRepository;


    @Transactional
    public boolean getAnalyzeResult(AnalyzeRequestDto analyzeRequestDto) {

        Analyzation analyzation = analyzeRequestDto.toAnalyzationEntity();
        //analyzationRepository.save(analyzation);
        return true;
    }
}
