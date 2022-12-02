package com.bowwowcare.sm.domain.survey;

import com.bowwowcare.sm.handler.BooleanToNYConverter;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class SadSurvey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //보호자와 떨어질 때/혼자 남겨지거나 낯선 장소에 있을 때
    @Convert(converter = BooleanToNYConverter.class)
    @Column
    private boolean alone;

    //혼날때
    @Convert(converter = BooleanToNYConverter.class)
    @Column
    private boolean scold;

    //차에 타 있을 때
    @Convert(converter = BooleanToNYConverter.class)
    @Column
    private boolean inCar;

    //빗질/발톱깍기/목욕 등 위생관리를 할 때
    @Convert(converter = BooleanToNYConverter.class)
    @Column
    private boolean healthcare;

    //낯선 소리가 나거나 낯선 사람을 봤을 때
    @Convert(converter = BooleanToNYConverter.class)
    @Column
    private boolean unfamiliar_stimulus;

    //기타
    /*
    @Column
    private String etc;

     */


    @Builder
    public SadSurvey(boolean alone, boolean scold, boolean inCar, boolean healthcare,
                     boolean unfamiliar_stimulus/*, String etc*/) {

        this.alone = alone;
        this.scold = scold;
        this.inCar = inCar;
        this.healthcare = healthcare;
        this.unfamiliar_stimulus = unfamiliar_stimulus;
        //this.etc = etc;
    }
}
