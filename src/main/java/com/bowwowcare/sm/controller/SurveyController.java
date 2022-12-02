package com.bowwowcare.sm.controller;

import com.bowwowcare.sm.dto.survey.AngrySurveySaveRequestDto;
import com.bowwowcare.sm.dto.survey.SadSurveySaveRequestDto;
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
import org.springframework.web.bind.annotation.*;

@Tag(name = "survey", description = "문진 API")
@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
@CrossOrigin //cors
public class SurveyController {

    private final SurveyService surveyService;

    @Operation(summary = "Sad 문진표 작성 api", description = "문진표 작성해서 결과 저장")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = SadSurveySaveRequestDto.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PostMapping(value = "/v1/survey/sad", headers = { "Content-type=application/json" })
    public ResponseEntity<?> saveSadSurvey(@RequestBody SadSurveySaveRequestDto sadSurveySaveRequestDto) {
        if(surveyService.saveSadSurveyResult(sadSurveySaveRequestDto)) {
            return new ResponseEntity<>("정상 작동", HttpStatus.OK);
        }
        return new ResponseEntity<>("정상 아님ㅠㅠ", HttpStatus.BAD_REQUEST);
    }


    @Operation(summary = "Angry 문진표 작성 api", description = "문진표 작성해서 결과 저장")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = AngrySurveySaveRequestDto.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PostMapping(value = "/v1/survey/angry", headers = { "Content-type=application/json" })
    public ResponseEntity<?> saveAngrySurvey(@RequestBody AngrySurveySaveRequestDto angrySurveySaveRequestDto) {
        if(surveyService.saveAngrySurveyResult(angrySurveySaveRequestDto)) {
            return new ResponseEntity<>("정상 작동", HttpStatus.OK);
        }
        return new ResponseEntity<>("정상 아님ㅠㅠ", HttpStatus.BAD_REQUEST);
    }

}
