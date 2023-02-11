package com.bowwowcare.sm.dto.survey;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AggressionResponseDto {

    Long id;
    String situation;
    String solution;
}
