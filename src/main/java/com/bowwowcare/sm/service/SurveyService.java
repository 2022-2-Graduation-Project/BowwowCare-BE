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
    private final AggressionQuestionRepository aggressionQuestionRepository;
    private final AnxietyQuestionRepository anxietyQuestionRepository;


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

    @Transactional
    public AggressionQuestion saveAggressionQuestion(AggressionQuestion aggressionQuestion) {
        return aggressionQuestionRepository.save(aggressionQuestion);
    }

    //TODO:추가
    @Transactional
    public AnxietyQuestion saveAnxietyQuestion(AnxietyQuestion anxietyQuestion) {
        return anxietyQuestionRepository.save(anxietyQuestion);
    }


    //aggression behavior 항목 전달
    public List<AggressionBehaviorResponseDto> selectAggressionBehavior() throws UnsupportedEncodingException{

        List<AggressionBehaviorResponseDto> result = new ArrayList<>();
        for(int i=0; i<aggressionBehaviorRepository.count(); i++){
            AggressionBehavior agb = aggressionBehaviorRepository.getOne(Long.valueOf(i+1));
            String content = new String(agb.getContent(), "UTF-8");
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
                AggressionQuestion aq = aggressionQuestionRepository.getOne(Long.valueOf(i+1));
                String content = new String(aq.getContent(), "UTF-8");
                result.add(SurveyQuestionResponseDto.builder()
                        .id(aq.getId())
                        .content(content)
                        .build());
            }
        }
        else {
            for(int i=0; i<anxietyQuestionRepository.count(); i++){
                AnxietyQuestion aq = anxietyQuestionRepository.getOne(Long.valueOf(i+1));
                String content = new String(aq.getContent(), "UTF-8");
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
                String situation = new String(aggressionResultRepository.getOne((long) x).getSituation(), "UTF-8");
                String solution = new String(aggressionResultRepository.getOne((long) x).getSolution(), "UTF-8");
                AggressionResponseDto ag = save_aggressionResult((long) x, situation, solution);
                result.add(ag);
            }
        }

        return result;
    }

    //anxiety 문진표 결과 반환
    public List<AnxietyResponseDto> findAnxietyResult(List<AnxietyRequestDto> anxietyRequestDtoList) throws UnsupportedEncodingException{

        List<AnxietyResponseDto> final_result = new ArrayList<>();

        for(int i=0; i<anxietyRequestDtoList.size(); i++){
            if(anxietyRequestDtoList.get(i).isChecked()){
                final_result = makeList();
                break;
            }
            List<AnxietyResponseDto> exception = new ArrayList<>();
            String s1 = new String(anxietyResultRepository.getOne(4L).getSituation(), "UTF-8");
            String s2 = new String(anxietyResultRepository.getOne(4L).getSolution1(), "UTF-8");
            List<String> s = new ArrayList<>();
            s.add(s2);
            AnxietyResponseDto a = save_anxietyResult(4L, s1, s);
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
