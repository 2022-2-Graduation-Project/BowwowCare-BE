package com.bowwowcare.sm.controller;

import com.bowwowcare.sm.dto.analyze.AnalyzeRequestDto;
import com.bowwowcare.sm.service.AnalyzeService;
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

@Tag(name = "analyzation", description = "분석 통신 API")
@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
@CrossOrigin
public class AnalyzeController {

    private final AnalyzeService analyzeService;

    @Operation(summary = "분석 결과 저장 api", description = "분석 결과 저장하기- 수정본")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = AnalyzeRequestDto.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PostMapping(value = "/v1/analyze", headers = { "Content-type=application/json" })
    public ResponseEntity<?> saveSadSurvey(@RequestBody AnalyzeRequestDto analyzeRequestDto) {
        if(analyzeService.saveAnalyzeResult(analyzeRequestDto)) {
            return new ResponseEntity<>("Success!", HttpStatus.OK);
        }
        return new ResponseEntity<>("저장 안됨", HttpStatus.BAD_REQUEST);
    }
}
