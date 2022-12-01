package com.bowwowcare.sm.domain.survey;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Survey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@Convert(converter = BooleanToNYConverter.class)
    //보호자와 떨어질 때/혼자 남겨지거나 낯선 장소에 있을 때
    @Column
    private String alone;

    //@Convert(converter = BooleanToNYConverter.class)
    //혼날때
    @Column
    private String scold;

    //@Convert(converter = BooleanToNYConverter.class)
    //차에 타 있을 때
    @Column
    private String inCar;

    //목욕할 때
    @Column
    private String bath;

    //낯선 소리가 나거나 낯선 장소에 있을 때
    @Column
    private String unfamiliar_stimulus;

    //빗질/발톱깍기/목욕 등 위생관리를 할 때
    @Column
    private String healthcare;

    //싫어하는 부위를 만질 때
    @Column
    private String hate;

    //밥그릇, 장난감과 같은 소유물을 만질 때
    @Column
    private String property;

    //낯선 동물 또는 사람을 만났을 때
    @Column
    private String unfamiliar_person;

    @Builder
    public Survey(String alone, String scold, String inCar, String bath,
                  String unfamiliar_stimulus, String healthcare, String hate,
                  String property, String unfamiliar_person) {

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
}
