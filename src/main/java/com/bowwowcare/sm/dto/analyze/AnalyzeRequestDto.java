package com.bowwowcare.sm.dto.analyze;

import com.bowwowcare.sm.domain.analyze.Analyzation;
import com.bowwowcare.sm.domain.analyze.Examination;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AnalyzeRequestDto {

    private String emotion;
    Examination examination = new Examination();


    @Builder
    public AnalyzeRequestDto(String emotion, Examination examination) {
        this.emotion = emotion;
        if(emotion.equals("ANGRY")){
            this.examination = examination;
            examination.setAlone(false);
            examination.setScold(false);
            examination.setInCar(false);
        }
        else {
            this.examination =examination;
            examination.setHate(false);
            examination.setProperty(false);
            examination.setUnfamiliar_person(false);
        }
    }



    public Analyzation toAnalyzationEntity() {
        return Analyzation.builder()
                .emotion(emotion)
                .healthcare(examination.isHealthcare())
                .hate(examination.isHate())
                .property(examination.isProperty())
                .unfamiliar_person(examination.isUnfamiliar_person())
                .unfamiliar_stimulus(examination.isUnfamiliar_stimulus())
                .alone(examination.isAlone())
                .scold(examination.isScold())
                .inCar(examination.isInCar())
                .build();

    }


}
