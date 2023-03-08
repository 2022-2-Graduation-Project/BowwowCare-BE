package com.bowwowcare.sm.dto.care;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AggressionCareResponseDto {

    private int id;
    private int count;
    private LocalDate missionDate;
    private String solution;
    private List<Integer> aggressionType;
    private LocalDate modifiedAt;
    private Boolean isCompleted;
}
