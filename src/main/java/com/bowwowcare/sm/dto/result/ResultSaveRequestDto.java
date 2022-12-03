package com.bowwowcare.sm.dto.result;

import com.bowwowcare.sm.domain.result.Result;
import com.bowwowcare.sm.domain.result.Results;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResultSaveRequestDto {

    private Boolean error;

    //private JsonObject results;

    Results results = new Results();

    /*
    @Builder
    public ResultSaveRequestDto(String error, JsonObject results) {
        this.error = error;
        this.results = results.deepCopy().getAsJsonObject()
    }

     */

    @Builder
    public ResultSaveRequestDto(Boolean error, Results results) {
        this.error = error;
        this.results = results;
    }

    public Result toResultEntity() {
        return Result.builder()
                .result(results.getPred())
                .build();
    }

    public ResultResponseDto toResultResponseDto() {
        return ResultResponseDto.builder()
                .result(results.getPred())
                .build();
    }

}
