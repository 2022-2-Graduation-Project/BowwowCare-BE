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
public class AnxietyCareResponseDto {

    private int id;
    private int count;
    private LocalDate missionDate;
    private String situation;
    private List<String> solution;
    private Boolean isCompleted;
}
