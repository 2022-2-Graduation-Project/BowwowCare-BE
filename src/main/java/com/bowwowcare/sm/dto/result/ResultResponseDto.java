package com.bowwowcare.sm.dto.result;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResultResponseDto {

    private String result;

    @Builder
    public ResultResponseDto(String result) {

        this.result = result;
    }

}
