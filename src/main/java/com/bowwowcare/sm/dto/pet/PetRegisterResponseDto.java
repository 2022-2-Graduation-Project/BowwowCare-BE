package com.bowwowcare.sm.dto.pet;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PetRegisterResponseDto {

    private Long id;
    private String name;

    @Builder
    public PetRegisterResponseDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
