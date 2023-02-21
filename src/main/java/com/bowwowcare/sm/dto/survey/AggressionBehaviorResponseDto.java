package com.bowwowcare.sm.dto.survey;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AggressionBehaviorResponseDto {

    Long aggressionId;
    int aggressionType;
    String content;
}
