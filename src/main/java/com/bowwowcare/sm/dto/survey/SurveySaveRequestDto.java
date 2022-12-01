package com.bowwowcare.sm.dto.survey;

import com.bowwowcare.sm.domain.survey.Survey;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SurveySaveRequestDto {

    private String alone;
    private String scold;
    private String inCar;
    private String bath;
    private String unfamiliar_stimulus;
    private String healthcare;
    private String hate;
    private String property;
    private String unfamiliar_person;

    @Builder
    public SurveySaveRequestDto(String alone, String scold, String inCar,
                                String bath, String unfamiliar_stimulus, String healthcare,
                                String hate, String property, String unfamiliar_person) {

        this.alone = alone;
        this.scold = scold;
        this.inCar = inCar;
        this.bath = bath;
        this.unfamiliar_stimulus = unfamiliar_stimulus;
        this.healthcare = healthcare;
        this.hate = hate;
        this.property = property;
        this.unfamiliar_person = unfamiliar_person;
    }

    public Survey toSurveyEntity(){
        return Survey.builder()
                .alone(alone)
                .scold(scold)
                .inCar(inCar)
                .bath(bath)
                .unfamiliar_stimulus(unfamiliar_stimulus)
                .healthcare(healthcare)
                .hate(hate)
                .property(property)
                .unfamiliar_person(unfamiliar_person)
                .build();
    }
}
