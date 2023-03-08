package com.bowwowcare.sm.controller;

import com.bowwowcare.sm.advice.result.SingleResult;
import com.bowwowcare.sm.dto.token.TokenRequestDto;
import com.bowwowcare.sm.dto.token.TokenResponseDto;
import com.bowwowcare.sm.dto.user.UserLoginRequestDto;
import com.bowwowcare.sm.dto.user.UserLoginResponseDto;
import com.bowwowcare.sm.dto.user.UserRegisterRequestDto;
import com.bowwowcare.sm.dto.user.UserRegisterResponseDto;
import com.bowwowcare.sm.service.ResponseService;
import com.bowwowcare.sm.service.SignService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "sign", description = "회원가입/로그인/토큰 API")
@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/api/v1/sign")
public class SignController {

    private final SignService signService;
    private final ResponseService responseService;

    @Operation(summary = "회원가입 api", description = "회원 가입")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = UserRegisterRequestDto.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PostMapping(value = "/join", headers = { "Content-type=application/json" })
    public UserRegisterResponseDto register(@RequestBody UserRegisterRequestDto userRegisterRequestDto) {
        UserRegisterResponseDto responseDto = signService.registerMember(userRegisterRequestDto);
        return responseDto;
    }


    @Operation(summary = "로그인 api", description = "로그인")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = UserLoginRequestDto.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PostMapping(value = "/login", headers = { "Content-type=application/json" })
    public UserLoginResponseDto login(@RequestBody UserLoginRequestDto userLoginRequestDto) {
        UserLoginResponseDto responseDto = signService.loginMember(userLoginRequestDto);
        return responseDto;
    }


    @Operation(summary = "토큰 재발급 api", description = "RefreshToken을 통해 토큰 재발급")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = TokenRequestDto.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PostMapping(value = "/reissue", headers = { "Content-type=application/json" })
    public SingleResult<TokenResponseDto> reIssue(@RequestBody TokenRequestDto tokenRequestDto) {
        TokenResponseDto responseDto = signService.reIssue(tokenRequestDto);
        return responseService.getSingleResult(responseDto);
    }
}
