package com.bowwowcare.sm.domain.analyze;

import lombok.Data;


@Data
public class Examination {

    private boolean healthcare;
    private boolean hate;
    private boolean property;
    private boolean unfamiliar_person;
    private boolean unfamiliar_stimulus;
    private boolean alone;
    private boolean scold;
    private boolean inCar;
}
