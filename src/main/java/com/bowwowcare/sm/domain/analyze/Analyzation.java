package com.bowwowcare.sm.domain.analyze;

import com.bowwowcare.sm.handler.BooleanToNYConverter;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Analyzation {

    //최종본
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String emotion;

    //Angry Survey
    //빗질/발톱깍기/목욕 등 위생관리를 할 때
    @Convert(converter = BooleanToNYConverter.class)
    @Column
    private boolean healthcare;

    //싫어하는 부위를 만질 때
    @Convert(converter = BooleanToNYConverter.class)
    @Column
    private boolean hate;

    //밥그릇, 장난감과 같은 소유물을 만질 때
    @Convert(converter = BooleanToNYConverter.class)
    @Column
    private boolean property;

    //낯선 동물 또는 사람을 만났을 때
    @Convert(converter = BooleanToNYConverter.class)
    @Column
    private boolean unfamiliar_person;

    //낯선 소리가 나거나 낯선 장소에 있을 때
    @Convert(converter = BooleanToNYConverter.class)
    @Column
    private boolean unfamiliar_stimulus;

    //Sad Survey
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



    @Builder
    public Analyzation(String emotion, boolean healthcare, boolean hate, boolean property,
                       boolean unfamiliar_person, boolean unfamiliar_stimulus, boolean alone,
                       boolean scold, boolean inCar) {

        this.emotion = emotion;
        this.healthcare = healthcare;
        this.hate = hate;
        this.property = property;
        this.unfamiliar_person = unfamiliar_person;
        this.unfamiliar_stimulus = unfamiliar_stimulus;
        this.alone = alone;
        this.scold = scold;
        this.inCar = inCar;
    }

}
