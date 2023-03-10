package com.bowwowcare.sm.dto.care;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CareMissionResponseDto {

    private int id;
    private int count;
    private LocalDate missionDate;
    private Boolean isCompleted;
    private String message;
}
