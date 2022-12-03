package com.bowwowcare.sm.controller;

import com.bowwowcare.sm.dto.result.ResultSaveRequestDto;
import com.bowwowcare.sm.dto.survey.SadSurveySaveRequestDto;
import com.bowwowcare.sm.service.ResultService;
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

@Tag(name = "analyze", description = "분석 통신 API")
@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
@CrossOrigin
public class AnalyzeResultController {

    private final ResultService resultService;

    @Operation(summary = "분석 결과 저장 api", description = "분석 결과 저장하기")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = SadSurveySaveRequestDto.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PostMapping(value = "/v1/analyze", headers = { "Content-type=application/json" })
    public ResponseEntity<?> saveSadSurvey(@RequestBody ResultSaveRequestDto resultSaveRequestDto) {
        if(resultService.saveAnalyzeResult(resultSaveRequestDto)) {
            return new ResponseEntity<String>(resultSaveRequestDto.getResults().getPred()+"저장됨!", HttpStatus.OK);
        }
        return new ResponseEntity<>("저장 안됨", HttpStatus.BAD_REQUEST);
    }


    @Operation(summary = "분석 결과 알림 api", description = "분석 결과 보여주기")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = SadSurveySaveRequestDto.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping(value = "/v1/analyze/{resultId}")
    public ResponseEntity<?> showAnalyzeResult(@PathVariable("resultId") Long resultId) {
        return new ResponseEntity<>(resultService.showAnalyzeResult(resultId), HttpStatus.OK);
    }


}
