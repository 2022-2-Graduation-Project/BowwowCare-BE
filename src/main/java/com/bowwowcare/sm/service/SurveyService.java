package com.bowwowcare.sm.service;

import com.bowwowcare.sm.domain.survey.AggressionBehavior;
import com.bowwowcare.sm.domain.survey.AggressionBehaviorRepository;
import com.bowwowcare.sm.domain.survey_result.AggressionResult;
import com.bowwowcare.sm.domain.survey_result.AggressionResultRepository;
import com.bowwowcare.sm.domain.survey_result.AnxietyResult;
import com.bowwowcare.sm.domain.survey_result.AnxietyResultRepository;
import com.bowwowcare.sm.dto.survey.AggressionRequestDto;
import com.bowwowcare.sm.dto.survey.AggressionResponseDto;
import com.bowwowcare.sm.dto.survey.AnxietyRequestDto;
import com.bowwowcare.sm.dto.survey.AnxietyResponseDto;
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
    private final AggressionBehaviorRepository aggressionBehaviorRepository;


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

    @Transactional
    public AggressionBehavior save_aggression_behavior(AggressionBehavior aggressionBehavior) {
        return aggressionBehaviorRepository.save(aggressionBehavior);
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

    //anxiety 문진표 결과 반환
    public List<AnxietyResponseDto> findAnxietyResult(AnxietyRequestDto anxietyRequestDto) throws UnsupportedEncodingException{

        List<AnxietyResponseDto> final_result = new ArrayList<>();

        if(anxietyRequestDto.isAnxiety1())
            final_result = makeList();
        else if(anxietyRequestDto.isAnxiety2())
            final_result = makeList();
        else if(anxietyRequestDto.isAnxiety3())
            final_result = makeList();
        else if(anxietyRequestDto.isAnxiety4())
            final_result = makeList();
        else if(anxietyRequestDto.isAnxiety5())
            final_result = makeList();
        else if(anxietyRequestDto.isAnxiety6())
            final_result = makeList();
        else if(anxietyRequestDto.isAnxiety7())
            final_result = makeList();
        else {
            List<AnxietyResponseDto> exception = new ArrayList<>();
            String s1 = new String(anxietyResultRepository.getOne(Long.valueOf(4)).getSituation(), "UTF-8");
            String s2 = new String(anxietyResultRepository.getOne(Long.valueOf(4)).getSolution1(), "UTF-8");
            List<String> s = new ArrayList<>();
            s.add(s2);
            AnxietyResponseDto a = save_anxietyResult(Long.valueOf(4), s1, s);
            exception.add(a);
            final_result = exception;
        }

        return final_result;
    }

    private AggressionResponseDto save_aggressionResult(Long id, String situation, String solution) {
        AggressionResponseDto ag = new AggressionResponseDto();
        ag.setId(id);
        ag.setSituation(situation);
        ag.setSolution(solution);
        return ag;
    }

    private AnxietyResponseDto save_anxietyResult(Long id, String situation, List<String> solution) {
        AnxietyResponseDto an = new AnxietyResponseDto();
        an.setId(id);
        an.setSituation(situation);
        an.setSolution(solution);
        return an;
    }

    private List<AnxietyResponseDto> makeList() throws UnsupportedEncodingException {

        List<AnxietyResponseDto> result = new ArrayList<>();

        String situation1 = new String(anxietyResultRepository.getOne(Long.valueOf(1)).getSituation(), "UTF-8");
        String solution1_1 = new String(anxietyResultRepository.getOne(Long.valueOf(1)).getSolution1(), "UTF-8");
        String solution1_2 = new String(anxietyResultRepository.getOne(Long.valueOf(1)).getSolution2(), "UTF-8");
        List<String> sol1 = new ArrayList<>();
        sol1.add(solution1_1);
        sol1.add(solution1_2);
        AnxietyResponseDto an1 = save_anxietyResult(Long.valueOf(1), situation1, sol1);
        result.add(an1);

        String situation2 = new String(anxietyResultRepository.getOne(Long.valueOf(2)).getSituation(), "UTF-8");
        String solution2_1 = new String(anxietyResultRepository.getOne(Long.valueOf(2)).getSolution1(), "UTF-8");
        String solution2_2 = new String(anxietyResultRepository.getOne(Long.valueOf(2)).getSolution2(), "UTF-8");
        String solution2_3 = new String(anxietyResultRepository.getOne(Long.valueOf(2)).getSolution3(), "UTF-8");
        List<String> sol2 = new ArrayList<>();
        sol2.add(solution2_1);
        sol2.add(solution2_2);
        sol2.add(solution2_3);
        AnxietyResponseDto an2 = save_anxietyResult(Long.valueOf(2), situation2, sol2);
        result.add(an2);

        String situation3 = new String(anxietyResultRepository.getOne(Long.valueOf(3)).getSituation(), "UTF-8");
        String solution3_1 = new String(anxietyResultRepository.getOne(Long.valueOf(3)).getSolution1(), "UTF-8");
        List<String> sol3 = new ArrayList<>();
        sol3.add(solution3_1);
        AnxietyResponseDto an3 = save_anxietyResult(Long.valueOf(3), situation3, sol3);
        result.add(an3);

        return result;
    }
}
