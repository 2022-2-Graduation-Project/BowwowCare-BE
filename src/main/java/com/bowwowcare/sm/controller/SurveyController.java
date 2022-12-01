package com.bowwowcare.sm.controller;

import com.bowwowcare.sm.dto.survey.SurveySaveRequestDto;
import com.bowwowcare.sm.service.SurveyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "survey", description = "문진 API")
@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class SurveyController {

    private final SurveyService surveyService;

    @Operation(summary = "문진표 작성 api", description = "문진표 작성해서 결과 저장(할까?)/전달")
    // 각 메서드에 responseMessage를
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = SurveySaveRequestDto.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PostMapping(value = "/v1/survey", headers = { "Content-type=application/json" })
    public ResponseEntity<?> saveSurvey(@RequestBody SurveySaveRequestDto surveySaveRequestDto) {
        if(surveyService.saveSurveyResult(surveySaveRequestDto)) {
            return new ResponseEntity<>("정상 작동", HttpStatus.OK);
        }
        return new ResponseEntity<>("정상 아님ㅠㅠ", HttpStatus.BAD_REQUEST);
    }

}
