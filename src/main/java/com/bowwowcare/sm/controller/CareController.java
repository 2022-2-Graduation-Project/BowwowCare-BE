package com.bowwowcare.sm.controller;

import com.bowwowcare.sm.config.security.member.MemberDetails;
import com.bowwowcare.sm.service.CareService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
}
