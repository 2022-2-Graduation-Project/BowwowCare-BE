package com.bowwowcare.sm.dto.survey;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnxietyResponseDto {

    Long id;
    String situation;
    List<String> solution;
}
