package com.bowwowcare.sm.controller;

import com.bowwowcare.sm.config.security.member.MemberDetails;
import com.bowwowcare.sm.dto.care.CareMissionRequestDto;
import com.bowwowcare.sm.service.CareService;
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

@Tag(name = "Care", description = "멍멍케어 API")
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@RestController
@CrossOrigin
public class CareController {

    private final CareService careService;

    @Operation(summary = "Care api", description = "공격행동 Care api")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping("/care/aggression/{petId}")
    public ResponseEntity<?> getAggressionCareList(@Parameter(hidden=true) @AuthenticationPrincipal MemberDetails memberDetails, @PathVariable("petId") int petId) {
        try {
            return new ResponseEntity<>(careService.findAggressionCareList(petId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(careService.findAggressionCareList(petId), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Care api", description = "분리불안 Care api")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping("/care/anxiety/{petId}")
    public ResponseEntity<?> getAnxietyCareList(@Parameter(hidden=true) @AuthenticationPrincipal MemberDetails memberDetails, @PathVariable("petId") int petId) {
        try {
            return new ResponseEntity<>(careService.findAnxietyCareList(petId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(careService.findAnxietyCareList(petId), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Care 실천 api", description = "미션 실천 여부 api")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = CareMissionRequestDto.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PostMapping(value = "/care/mission/{type}", headers = { "Content-type=application/json" })
    public ResponseEntity<?> saveMissionCount(@Parameter(hidden = true) @AuthenticationPrincipal MemberDetails memberDetails, @PathVariable("type") String type,
                                              @RequestBody CareMissionRequestDto careMissionRequestDto) {
        try {
            return new ResponseEntity<>(careService.CalCareMissionCount(careMissionRequestDto, type, memberDetails), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(careService.CalCareMissionCount(careMissionRequestDto, type, memberDetails), HttpStatus.BAD_REQUEST);
        }
    }
}
