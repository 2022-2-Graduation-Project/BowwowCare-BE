package com.bowwowcare.sm.dto.survey;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AggressionRequestDto {

    private boolean aggression1;
    private boolean aggression2;
    private boolean aggression3;
    private boolean aggression4;
    private boolean aggression5;
    private boolean aggression6;
    private boolean aggression7;
}
