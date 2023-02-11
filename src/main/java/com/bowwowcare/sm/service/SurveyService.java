package com.bowwowcare.sm.service;

import com.bowwowcare.sm.domain.survey_result.AggressionResult;
import com.bowwowcare.sm.domain.survey_result.AggressionResultRepository;
import com.bowwowcare.sm.domain.survey_result.AnxietyResult;
import com.bowwowcare.sm.domain.survey_result.AnxietyResultRepository;
import com.bowwowcare.sm.dto.survey.AggressionRequestDto;
import com.bowwowcare.sm.dto.survey.AggressionResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SurveyService {

    private final AggressionResultRepository aggressionResultRepository;
    private final AnxietyResultRepository anxietyResultRepository;

    /**
     * aggression 문진 응답에 따른 situation, solution 저장 위한 메서드
     * DataInit
     */
    @Transactional
    public AggressionResult save_aggression(AggressionResult aggressionResult) {
        return aggressionResultRepository.save(aggressionResult);
    }

    /**
     * anxiety 문진 응답에 따른 situation, solution 저장 위한 메서드
     * DataInit
     */
    @Transactional
    public AnxietyResult save_anxiety(AnxietyResult anxietyResult) {
        return anxietyResultRepository.save(anxietyResult);
    }


    //aggression 문진표 결과 반환
    @Transactional
    public List<AggressionResponseDto> findAggressionResult(AggressionRequestDto aggressionRequestDto) throws UnsupportedEncodingException {

        List<AggressionResponseDto> result = new ArrayList<>();

        if(aggressionRequestDto.isAggression1()) {
            String situation = new String(aggressionResultRepository.getOne(Long.valueOf(1)).getSituation(), "UTF-8");
            String solution = new String(aggressionResultRepository.getOne(Long.valueOf(1)).getSolution(), "UTF-8");
            AggressionResponseDto ag1 = save_aggressionResult(Long.valueOf(1), situation,solution);
            result.add(ag1);
        }
        if(aggressionRequestDto.isAggression2()) {
            String situation = new String(aggressionResultRepository.getOne(Long.valueOf(2)).getSituation(), "UTF-8");
            String solution = new String(aggressionResultRepository.getOne(Long.valueOf(2)).getSolution(), "UTF-8");
            AggressionResponseDto ag2 = save_aggressionResult(Long.valueOf(2), situation,solution);
            result.add(ag2);
        }
        if(aggressionRequestDto.isAggression3()) {
            String situation = new String(aggressionResultRepository.getOne(Long.valueOf(3)).getSituation(), "UTF-8");
            String solution = new String(aggressionResultRepository.getOne(Long.valueOf(3)).getSolution(), "UTF-8");
            AggressionResponseDto ag3 = save_aggressionResult(Long.valueOf(3), situation,solution);
            result.add(ag3);
        }
        if(aggressionRequestDto.isAggression4()) {
            String situation = new String(aggressionResultRepository.getOne(Long.valueOf(4)).getSituation(), "UTF-8");
            String solution = new String(aggressionResultRepository.getOne(Long.valueOf(4)).getSolution(), "UTF-8");
            AggressionResponseDto ag4 = save_aggressionResult(Long.valueOf(4), situation,solution);
            result.add(ag4);
        }
        if(aggressionRequestDto.isAggression5()) {
            String situation = new String(aggressionResultRepository.getOne(Long.valueOf(5)).getSituation(), "UTF-8");
            String solution = new String(aggressionResultRepository.getOne(Long.valueOf(5)).getSolution(), "UTF-8");
            AggressionResponseDto ag5 = save_aggressionResult(Long.valueOf(5), situation,solution);
            result.add(ag5);
        }
        if(aggressionRequestDto.isAggression6()) {
            String situation = new String(aggressionResultRepository.getOne(Long.valueOf(6)).getSituation(), "UTF-8");
            String solution = new String(aggressionResultRepository.getOne(Long.valueOf(6)).getSolution(), "UTF-8");
            AggressionResponseDto ag6 = save_aggressionResult(Long.valueOf(6), situation,solution);
            result.add(ag6);
        }
        if(aggressionRequestDto.isAggression7()) {
            String situation = new String(aggressionResultRepository.getOne(Long.valueOf(7)).getSituation(), "UTF-8");
            String solution = new String(aggressionResultRepository.getOne(Long.valueOf(7)).getSolution(), "UTF-8");
            AggressionResponseDto ag7 = save_aggressionResult(Long.valueOf(7), situation,solution);
            result.add(ag7);
        }

        return result;
    }

    private AggressionResponseDto save_aggressionResult(Long id, String situation, String solution) {
        AggressionResponseDto ag = new AggressionResponseDto();
        ag.setId(id);
        ag.setSituation(situation);
        ag.setSolution(solution);
        return ag;
    }
}
