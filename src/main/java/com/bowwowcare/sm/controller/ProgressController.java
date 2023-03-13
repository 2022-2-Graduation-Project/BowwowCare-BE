package com.bowwowcare.sm.controller;

import com.bowwowcare.sm.config.security.member.MemberDetails;
import com.bowwowcare.sm.dto.survey.AnxietyRequestDto;
import com.bowwowcare.sm.service.ProgressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "progress", description = "문진 경과 알림 API")
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@RestController
@CrossOrigin
public class ProgressController {

    private final ProgressService progressService;

    @Operation(summary = "AnxietyProgress api", description = "Anxiety history 비교 - 칭찬 or 혼내기")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = AnxietyRequestDto.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping(value = "/progress/anxiety/{petId}")
    public ResponseEntity<?> getAnxietyProgress(@Parameter(hidden=true) @AuthenticationPrincipal MemberDetails memberDetails, @RequestBody List<AnxietyRequestDto> anxietyRequestDtoList,
                                                @PathVariable("petId") int petId) {
        try {
            return new ResponseEntity<>(progressService.calAnxietyProgress(anxietyRequestDtoList, petId), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(progressService.calAnxietyProgress(anxietyRequestDtoList, petId), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "AggressionProgress api", description = "Aggression history 비교 - 칭찬 or 혼내기")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping(value = "/progress/aggression/{historyId}")
    public ResponseEntity<?> getAggressionProgress(@Parameter(hidden=true) @AuthenticationPrincipal MemberDetails memberDetails,
                                                   @PathVariable("historyId") int historyId) {
        try {
            return new ResponseEntity<>(progressService.calAggressionProgress(historyId), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(progressService.calAggressionProgress(historyId), HttpStatus.BAD_REQUEST);
        }
    }
}
