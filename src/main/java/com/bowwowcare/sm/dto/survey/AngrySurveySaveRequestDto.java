package com.bowwowcare.sm.dto.survey;

import com.bowwowcare.sm.domain.survey.AngrySurvey;
import com.bowwowcare.sm.domain.survey.SadSurvey;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AngrySurveySaveRequestDto {

    private boolean healthcare;
    private boolean hate;
    private boolean property;
    private boolean unfamiliar_person;
    private boolean unfamiliar_stimulus;

    @Builder
    public AngrySurveySaveRequestDto(boolean healthcare, boolean hate, boolean property,
                                     boolean unfamiliar_person, boolean unfamiliar_stimulus) {


        this.healthcare = healthcare;
        this.hate = hate;
        this.property = property;
        this.unfamiliar_person = unfamiliar_person;
        this.unfamiliar_stimulus = unfamiliar_stimulus;
    }

    public AngrySurvey toAngrySurveyEntity(){
        return AngrySurvey.builder()
                .healthcare(healthcare)
                .hate(hate)
                .property(property)
                .unfamiliar_person(unfamiliar_person)
                .unfamiliar_stimulus(unfamiliar_stimulus)
                .build();
    }
}
