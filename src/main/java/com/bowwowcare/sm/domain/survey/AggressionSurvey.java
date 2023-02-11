package com.bowwowcare.sm.domain.survey;

import com.bowwowcare.sm.handler.BooleanToNYConverter;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "aggression_survey")
public class AggressionSurvey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Convert(converter = BooleanToNYConverter.class)
    @Column
    private boolean aggression1;

    @Convert(converter = BooleanToNYConverter.class)
    @Column
    private boolean aggression2;

    @Convert(converter = BooleanToNYConverter.class)
    @Column
    private boolean aggression3;

    @Convert(converter = BooleanToNYConverter.class)
    @Column
    private boolean aggression4;

    @Convert(converter = BooleanToNYConverter.class)
    @Column
    private boolean aggression5;

    @Convert(converter = BooleanToNYConverter.class)
    @Column
    private boolean aggression6;

    @Convert(converter = BooleanToNYConverter.class)
    @Column
    private boolean aggression7;
}
