package com.bowwowcare.sm.service;

import com.bowwowcare.sm.domain.result.Result;
import com.bowwowcare.sm.domain.result.ResultRepository;
import com.bowwowcare.sm.dto.result.ResultResponseDto;
import com.bowwowcare.sm.dto.result.ResultSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ResultService {

    private final ResultRepository resultRepository;

    @Transactional
    public Boolean saveAnalyzeResult(ResultSaveRequestDto resultSaveRequestDto) {
        Result new_result = resultSaveRequestDto.toResultEntity();
        resultRepository.save(new_result);
        return true;
    }

    public ResultResponseDto showAnalyzeResult(Long resultId) {
        ResultResponseDto responseDto = new ResultResponseDto(resultRepository.findById(resultId).get().getResult());
        return responseDto;

    }


}
