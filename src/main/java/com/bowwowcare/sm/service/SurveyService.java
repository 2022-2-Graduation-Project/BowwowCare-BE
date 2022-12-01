package com.bowwowcare.sm.service;

import com.bowwowcare.sm.domain.survey.Survey;
import com.bowwowcare.sm.domain.survey.SurveyRepository;
import com.bowwowcare.sm.dto.survey.SurveySaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class SurveyService {

    private final SurveyRepository surveyRepository;

    @Transactional
    public Boolean saveSurveyResult(SurveySaveRequestDto surveySaveRequestDto) {
        Survey new_survey = surveySaveRequestDto.toSurveyEntity();
        surveyRepository.save(new_survey);
        return true;
    }
}
