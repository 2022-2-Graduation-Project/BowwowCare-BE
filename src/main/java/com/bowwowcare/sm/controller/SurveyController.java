package com.bowwowcare.sm.controller;

import com.bowwowcare.sm.dto.history.HistoryRequestDto;
import com.bowwowcare.sm.dto.survey.AggressionRequestDto;
import com.bowwowcare.sm.dto.survey.AnxietyRequestDto;
import com.bowwowcare.sm.service.HistoryService;
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

import java.util.List;

@Tag(name = "survey & behavior", description = "문진 API")
@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
@CrossOrigin
public class SurveyController {

    private final SurveyService surveyService;
    private final HistoryService historyService;

    @Operation(summary = "공격행동 문진표 api", description = "문진표 작성 후 상황별 솔루션 반환 - 공격행동")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = AggressionRequestDto.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PostMapping(value = "/v1/survey/aggression", headers = { "Content-type=application/json" })
    public ResponseEntity<?> findAggressionSurveyResult(@RequestBody List<AggressionRequestDto> aggressionRequestDtoList) {
        //return new ResponseEntity<>(surveyService.findResult(aggressionRequestDto), HttpStatus.OK);
        try {
            return new ResponseEntity<>(surveyService.findAggressionResult(aggressionRequestDtoList), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>("인코딩 오류!", HttpStatus.BAD_REQUEST);
        }
    }


    @Operation(summary = "분리불안 문진표 api", description = "문진표 작성 후 상황별 솔루션 반환 - 분리불안")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = AnxietyRequestDto.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PostMapping(value = "/v1/survey/anxiety", headers = { "Content-type=application/json" })
    public ResponseEntity<?> findAnxietySurveyResult(@RequestBody List<AnxietyRequestDto> anxietyRequestDtoList) {
        //return new ResponseEntity<>(surveyService.findResult(aggressionRequestDto), HttpStatus.OK);
        try {
            return new ResponseEntity<>(surveyService.findAnxietyResult(anxietyRequestDtoList), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>("인코딩 오류!", HttpStatus.BAD_REQUEST);
        }
    }


    @Operation(summary = "문진 결과 저장 api", description = "문진표 작성 후 상황별 솔루션 쌍을 반려견 별로 저장")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = HistoryRequestDto.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PostMapping(value = "/v1/survey/result", headers = { "Content-type=application/json" })
    public ResponseEntity<?> saveSurveyResult(@RequestBody HistoryRequestDto historyRequestDto) {
        try {
            return new ResponseEntity<>(historyService.saveHistory(historyRequestDto), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>("오류!!", HttpStatus.BAD_REQUEST);
        }
    }


    @Operation(summary = "Aggression Behavior api", description = "공격행동 진단 항목 가져오기")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping("/v1/behaviors")
    public ResponseEntity<?> returnAggressionBehaviorContent() {
        try {
            return new ResponseEntity<>(surveyService.selectAggressionBehavior(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("오류!!", HttpStatus.BAD_REQUEST);
        }
    }


    @Operation(summary = "Survey Question api", description = "문진 질문 리스트 가져오기")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping("/v1/questions/{type}")
    public ResponseEntity<?> returnSurveyQuestion(@PathVariable("type") String type) {
        try {
            return new ResponseEntity<>(surveyService.selectSurveyQuestion(type), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("오류!!", HttpStatus.BAD_REQUEST);
        }
    }
}
