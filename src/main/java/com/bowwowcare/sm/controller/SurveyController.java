package com.bowwowcare.sm.controller;

import com.bowwowcare.sm.dto.survey.AggressionRequestDto;
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
@CrossOrigin
public class SurveyController {

    private final SurveyService surveyService;

    @Operation(summary = "공격행동 문진표 api", description = "문진표 작성 후 상황별 솔루션 반환 - 공격행동")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = AggressionRequestDto.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PostMapping(value = "/v1/survey/aggression", headers = { "Content-type=application/json" })
    public ResponseEntity<?> findAggressionSurveyResult(@RequestBody AggressionRequestDto aggressionRequestDto) {
        //return new ResponseEntity<>(surveyService.findResult(aggressionRequestDto), HttpStatus.OK);
        try {
            return new ResponseEntity<>(surveyService.findAggressionResult(aggressionRequestDto), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>("인코딩 오류!", HttpStatus.BAD_REQUEST);
        }
    }
}
