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
public class CareMissionRequestDto {

    private int id;
    private LocalDate missionDate;
}
