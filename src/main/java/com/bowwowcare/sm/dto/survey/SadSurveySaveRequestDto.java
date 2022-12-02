package com.bowwowcare.sm.dto.survey;

import com.bowwowcare.sm.domain.survey.SadSurvey;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Builder;

@Getter
@NoArgsConstructor
public class SadSurveySaveRequestDto {

    private boolean alone;
    private boolean scold;
    private boolean inCar;
    private boolean healthcare;
    private boolean unfamiliar_stimulus;

    @Builder
    public SadSurveySaveRequestDto(boolean alone, boolean scold, boolean inCar,
                                   boolean healthcare, boolean unfamiliar_stimulus) {

        this.alone = alone;
        this.scold = scold;
        this.inCar = inCar;
        this.healthcare = healthcare;
        this.unfamiliar_stimulus = unfamiliar_stimulus;
    }

    public SadSurvey toSadSurveyEntity(){
        return SadSurvey.builder()
                .alone(alone)
                .scold(scold)
                .inCar(inCar)
                .healthcare(healthcare)
                .unfamiliar_stimulus(unfamiliar_stimulus)
                .build();
    }
}
