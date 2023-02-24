package com.bowwowcare.sm.controller;

import com.bowwowcare.sm.dto.history.AnxietyHistoryRequestDto;
import com.bowwowcare.sm.service.HistoryService;
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

@Tag(name = "history", description = "문진 결과 저장 API")
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@RestController
@CrossOrigin
public class HistoryController {

    private final HistoryService historyService;

    @Operation(summary = "분리불안 문진 결과 저장 api", description = "문진표 작성 후 상황별 솔루션 쌍을 반려견 별로 저장")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = AnxietyHistoryRequestDto.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PostMapping(value = "/survey/result/anxiety", headers = { "Content-type=application/json" })
    public ResponseEntity<?> saveSurveyResult(@RequestBody AnxietyHistoryRequestDto anxietyHistoryRequestDto) {
        try {
            return new ResponseEntity<>(historyService.saveAnxietyHistory(anxietyHistoryRequestDto), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>("오류!!", HttpStatus.BAD_REQUEST);
        }
    }
}
