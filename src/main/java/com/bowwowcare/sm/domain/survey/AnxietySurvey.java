package com.bowwowcare.sm.domain.survey;

import com.bowwowcare.sm.handler.BooleanToNYConverter;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "anxiety_survey")
public class AnxietySurvey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Convert(converter = BooleanToNYConverter.class)
    @Column
    private boolean anxiety1;

    @Convert(converter = BooleanToNYConverter.class)
    @Column
    private boolean anxiety2;

    @Convert(converter = BooleanToNYConverter.class)
    @Column
    private boolean anxiety3;

    @Convert(converter = BooleanToNYConverter.class)
    @Column
    private boolean anxiety4;

    @Convert(converter = BooleanToNYConverter.class)
    @Column
    private boolean anxiety5;

    @Convert(converter = BooleanToNYConverter.class)
    @Column
    private boolean anxiety6;

    @Convert(converter = BooleanToNYConverter.class)
    @Column
    private boolean anxiety7;
}
