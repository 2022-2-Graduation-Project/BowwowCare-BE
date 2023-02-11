package com.bowwowcare.sm.service;

import com.bowwowcare.sm.domain.survey_result.AggressionResult;
import com.bowwowcare.sm.domain.survey_result.AggressionResultRepository;
import com.bowwowcare.sm.domain.survey_result.AnxietyResult;
import com.bowwowcare.sm.domain.survey_result.AnxietyResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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


}
