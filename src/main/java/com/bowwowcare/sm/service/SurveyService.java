package com.bowwowcare.sm.service;

import com.bowwowcare.sm.domain.survey.AngrySurvey;
import com.bowwowcare.sm.domain.survey.AngrySurveyRepository;
import com.bowwowcare.sm.domain.survey.SadSurvey;
import com.bowwowcare.sm.domain.survey.SadSurveyRepository;
import com.bowwowcare.sm.dto.survey.AngrySurveySaveRequestDto;
import com.bowwowcare.sm.dto.survey.SadSurveySaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class SurveyService {

    private final SadSurveyRepository sadSurveyRepository;
    private final AngrySurveyRepository angrySurveyRepository;

    @Transactional
    public Boolean saveSadSurveyResult(SadSurveySaveRequestDto sadSurveySaveRequestDto) {
        SadSurvey new_SadSurvey = sadSurveySaveRequestDto.toSadSurveyEntity();
        sadSurveyRepository.save(new_SadSurvey);
        return true;
    }

    @Transactional
    public Boolean saveAngrySurveyResult(AngrySurveySaveRequestDto angrySurveySaveRequestDto) {
        AngrySurvey new_AngrySurvey = angrySurveySaveRequestDto.toAngrySurveyEntity();
        angrySurveyRepository.save(new_AngrySurvey);
        return true;
    }
}
