package com.bowwowcare.sm.controller;

import com.bowwowcare.sm.dto.survey.AngrySurveySaveRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "test", description = "테스트 api")
@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class TestController {

    @Operation(summary = "테스트 get api", description = "^~^")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = AngrySurveySaveRequestDto.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping("/v1/hello")
    public ResponseEntity<?> helloWorld() {
        return new ResponseEntity<>("안녕^~^ 바우와우바우와우", HttpStatus.OK);
    }
}
