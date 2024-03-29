package com.bowwowcare.sm.service;

import com.bowwowcare.sm.domain.survey.AggressionBehavior;
import com.bowwowcare.sm.domain.survey.AggressionBehaviorRepository;
import com.bowwowcare.sm.domain.survey_question.AggressionQuestion;
import com.bowwowcare.sm.domain.survey_question.AggressionQuestionRepository;
import com.bowwowcare.sm.domain.survey_question.AnxietyQuestion;
import com.bowwowcare.sm.domain.survey_question.AnxietyQuestionRepository;
import com.bowwowcare.sm.domain.survey_result.AggressionResult;
import com.bowwowcare.sm.domain.survey_result.AggressionResultRepository;
import com.bowwowcare.sm.domain.survey_result.AnxietyResult;
import com.bowwowcare.sm.domain.survey_result.AnxietyResultRepository;
import com.bowwowcare.sm.dto.survey.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SurveyService {

    private final AggressionResultRepository aggressionResultRepository;
    private final AnxietyResultRepository anxietyResultRepository;
    private final AggressionBehaviorRepository aggressionBehaviorRepository;
    private final AggressionQuestionRepository aggressionQuestionRepository;
    private final AnxietyQuestionRepository anxietyQuestionRepository;


    /**
     * aggression 문진 응답에 따른 situation, solution 저장 위한 메서드
     * DataInit
     */
    @Transactional
    public AggressionResult saveAggression(AggressionResult aggressionResult) {
        if(aggressionResultRepository.exists(Example.of(aggressionResult))) {
            AggressionResult result = aggressionResultRepository.findAggressionResultBySituation(aggressionResult.getSituation());
            return aggressionResultRepository.getOne(result.getId());
        }
        else {
            return aggressionResultRepository.save(aggressionResult);
        }
    }

    /**
     * anxiety 문진 응답에 따른 situation, solution 저장 위한 메서드
     * DataInit
     */
    @Transactional
    public AnxietyResult saveAnxiety(AnxietyResult anxietyResult) {
        if(anxietyResultRepository.exists(Example.of(anxietyResult))) {
            AnxietyResult result = anxietyResultRepository.findAnxietyResultBySituation(anxietyResult.getSituation());
            return anxietyResultRepository.getOne(result.getId());
        }
        else {
            return anxietyResultRepository.save(anxietyResult);
        }
        //return anxietyResultRepository.save(anxietyResult);
    }

    @Transactional
    public AggressionBehavior saveAggressionBehavior(AggressionBehavior aggressionBehavior) {
        if(aggressionBehaviorRepository.exists(Example.of(aggressionBehavior))) {
            AggressionBehavior behavior = aggressionBehaviorRepository.findAggressionBehaviorByContent(aggressionBehavior.getContent());
            return aggressionBehaviorRepository.getOne(behavior.getId());
        }
        else {
            return aggressionBehaviorRepository.save(aggressionBehavior);
        }
        //return aggressionBehaviorRepository.save(aggressionBehavior);
    }

    @Transactional
    public AggressionQuestion saveAggressionQuestion(AggressionQuestion aggressionQuestion) {
        if(aggressionQuestionRepository.exists(Example.of(aggressionQuestion))) {
            AggressionQuestion question = aggressionQuestionRepository.findAggressionQuestionByContent(aggressionQuestion.getContent());
            return aggressionQuestionRepository.getOne(question.getId());
        }
        else {
            return aggressionQuestionRepository.save(aggressionQuestion);
        }
        //return aggressionQuestionRepository.save(aggressionQuestion);
    }

    @Transactional
    public AnxietyQuestion saveAnxietyQuestion(AnxietyQuestion anxietyQuestion) {
        if(anxietyQuestionRepository.exists(Example.of(anxietyQuestion))) {
            AnxietyQuestion question = anxietyQuestionRepository.findAnxietyQuestionByContent(anxietyQuestion.getContent());
            return anxietyQuestionRepository.getOne(question.getId());
        }
        else {
            return anxietyQuestionRepository.save(anxietyQuestion);
        }
        //return anxietyQuestionRepository.save(anxietyQuestion);
    }


    //aggression behavior 항목 전달
    public List<AggressionBehaviorResponseDto> selectAggressionBehavior() throws UnsupportedEncodingException{

        List<AggressionBehaviorResponseDto> result = new ArrayList<>();
        for(int i=0; i<aggressionBehaviorRepository.count(); i++){
            AggressionBehavior agb = aggressionBehaviorRepository.getOne((long) (i + 1));
            String content = new String(agb.getContent(), StandardCharsets.UTF_8);
            result.add(AggressionBehaviorResponseDto.builder()
                    .aggressionId(agb.getId())
                    .aggressionType(agb.getAggressionType())
                    .content(content)
                    .build());
        }
        return result;
    }

    //survey question 항목 전달
    public List<SurveyQuestionResponseDto> selectSurveyQuestion(String type) throws UnsupportedEncodingException {

        List<SurveyQuestionResponseDto> result = new ArrayList<>();
        if(type.equals("aggression")) {
            for(int i=0; i<aggressionQuestionRepository.count(); i++){
                AggressionQuestion aq = aggressionQuestionRepository.getOne((long) (i + 1));
                String content = new String(aq.getContent(), StandardCharsets.UTF_8);
                result.add(SurveyQuestionResponseDto.builder()
                        .id(aq.getId())
                        .content(content)
                        .build());
            }
        }
        else {
            for(int i=0; i<anxietyQuestionRepository.count(); i++){
                AnxietyQuestion aq = anxietyQuestionRepository.getOne((long) (i + 1));
                String content = new String(aq.getContent(), StandardCharsets.UTF_8);
                result.add(SurveyQuestionResponseDto.builder()
                        .id(aq.getId())
                        .content(content)
                        .build());
            }
        }
        return result;
    }


    //aggression 문진표 결과 반환
    @Transactional
    public List<AggressionResponseDto> findAggressionResult(List<AggressionRequestDto> aggressionRequestDtoList) throws UnsupportedEncodingException {

        List<AggressionResponseDto> result = new ArrayList<>();

        for(int i=0; i<aggressionRequestDtoList.size(); i++) {
            int x = aggressionRequestDtoList.get(i).getId();
            if(aggressionRequestDtoList.get(i).isChecked()) {
                String situation = new String(aggressionResultRepository.getOne((long) x).getSituation(), StandardCharsets.UTF_8);
                String solution = new String(aggressionResultRepository.getOne((long) x).getSolution(), StandardCharsets.UTF_8);
                AggressionResponseDto ag = saveAggressionResult((long) x, situation, solution);
                result.add(ag);
            }
        }

        return result;
    }


    //anxiety 문진표 결과 반환
    @Transactional
    public List<AnxietyResponseDto> findAnxietyResult(List<AnxietyRequestDto> anxietyRequestDtoList) throws UnsupportedEncodingException{

        List<AnxietyResponseDto> final_result = new ArrayList<>();

        for(int i=0; i<anxietyRequestDtoList.size(); i++){
            if(anxietyRequestDtoList.get(i).isChecked()){
                final_result = makeList();
                break;
            }
            List<AnxietyResponseDto> exception = new ArrayList<>();
            String s1 = new String(anxietyResultRepository.getOne(4L).getSituation(), StandardCharsets.UTF_8);
            String s2 = new String(anxietyResultRepository.getOne(4L).getSolution1(), StandardCharsets.UTF_8);
            List<String> s = new ArrayList<>();
            s.add(s2);
            AnxietyResponseDto a = saveAnxietyResult(4L, s1, s);
            exception.add(a);
            final_result = exception;
        }
        return final_result;

    }

    private AggressionResponseDto saveAggressionResult(Long id, String situation, String solution) {
        AggressionResponseDto ag = new AggressionResponseDto();
        ag.setId(id);
        ag.setSituation(situation);
        ag.setSolution(solution);
        return ag;
    }

    private AnxietyResponseDto saveAnxietyResult(Long id, String situation, List<String> solution) {
        AnxietyResponseDto an = new AnxietyResponseDto();
        an.setId(id);
        an.setSituation(situation);
        an.setSolution(solution);
        return an;
    }

    private List<AnxietyResponseDto> makeList() throws UnsupportedEncodingException {

        List<AnxietyResponseDto> result = new ArrayList<>();

        String situation1 = new String(anxietyResultRepository.getOne(1L).getSituation(), StandardCharsets.UTF_8);
        String solution1_1 = new String(anxietyResultRepository.getOne(1L).getSolution1(), StandardCharsets.UTF_8);
        String solution1_2 = new String(anxietyResultRepository.getOne(1L).getSolution2(), StandardCharsets.UTF_8);
        List<String> sol1 = new ArrayList<>();
        sol1.add(solution1_1);
        sol1.add(solution1_2);
        AnxietyResponseDto an1 = saveAnxietyResult(1L, situation1, sol1);
        result.add(an1);

        String situation2 = new String(anxietyResultRepository.getOne(2L).getSituation(), StandardCharsets.UTF_8);
        String solution2_1 = new String(anxietyResultRepository.getOne(2L).getSolution1(), StandardCharsets.UTF_8);
        String solution2_2 = new String(anxietyResultRepository.getOne(2L).getSolution2(), StandardCharsets.UTF_8);
        String solution2_3 = new String(anxietyResultRepository.getOne(2L).getSolution3(), StandardCharsets.UTF_8);
        List<String> sol2 = new ArrayList<>();
        sol2.add(solution2_1);
        sol2.add(solution2_2);
        sol2.add(solution2_3);
        AnxietyResponseDto an2 = saveAnxietyResult(2L, situation2, sol2);
        result.add(an2);

        String situation3 = new String(anxietyResultRepository.getOne(3L).getSituation(), StandardCharsets.UTF_8);
        String solution3_1 = new String(anxietyResultRepository.getOne(3L).getSolution1(), StandardCharsets.UTF_8);
        List<String> sol3 = new ArrayList<>();
        sol3.add(solution3_1);
        AnxietyResponseDto an3 = saveAnxietyResult(3L, situation3, sol3);
        result.add(an3);

        return result;
    }
}
