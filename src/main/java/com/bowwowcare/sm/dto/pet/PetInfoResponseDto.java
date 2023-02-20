package com.bowwowcare.sm.dto.pet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PetInfoResponseDto {

    private Long id;
    private String name;
    private String gender;
    private String petImg;
    private LocalDate birthDate;
    private LocalDate adoptionDate;
    private Long memberId;
}
