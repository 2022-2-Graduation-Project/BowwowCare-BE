package com.bowwowcare.sm.controller;

import com.bowwowcare.sm.advice.exception.AuthenticationEntryPointException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "exception", description = "예외 처리 API")
@RequiredArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/api/v1/exception")
public class ExceptionController {

    @Operation(summary = "예외 처리 api", description = "예외 처리 - 인증 실패")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping(value = "/entry")
    public void EntryPointException() {
        throw new AuthenticationEntryPointException();
    }

    @Operation(summary = "예외 처리 api", description = "예외 처리 - 인가 거부")
    @GetMapping(value = "/denied")
    public void AccessDeniedException() {
        throw new AccessDeniedException("");
    }
}
