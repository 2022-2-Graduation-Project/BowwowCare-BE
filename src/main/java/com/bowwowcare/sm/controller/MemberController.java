package com.bowwowcare.sm.controller;

import com.bowwowcare.sm.config.security.member.MemberDetails;
import com.bowwowcare.sm.dto.user.UserInfoUpdateRequestDto;
import com.bowwowcare.sm.dto.user.UserThemeRequestDto;
import com.bowwowcare.sm.service.MemberService;
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

@Tag(name = "user", description = "유저 API")
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@RestController
@CrossOrigin
public class MemberController {

    private final MemberService memberService;

    @Operation(summary = "유저 정보 수정 api", description = "유저 정보 업데이트 - 이름, 사진, 현재 테마 변경")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = UserInfoUpdateRequestDto.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PutMapping(value = "/user", headers = { "Content-type=application/json" })
    public ResponseEntity<?> updateMemberInfo(@Parameter(hidden=true) @AuthenticationPrincipal MemberDetails memberDetails, @RequestBody UserInfoUpdateRequestDto userInfoUpdateRequestDto) {
        try {
            return new ResponseEntity<>(memberService.updateUserInfo(memberDetails, userInfoUpdateRequestDto), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(memberService.updateUserInfo(memberDetails, userInfoUpdateRequestDto), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "유저 정보 조회 api", description = "유저 정보 상세 조회 - id, email, 이름, 사진, 현재 테마 번호, 획득 테마 List")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping(value = "/user")
    public ResponseEntity<?> getMemberInfo(@Parameter(hidden=true) @AuthenticationPrincipal MemberDetails memberDetails){
        try {
            //return new ResponseEntity<>(memberService.findUserInfo(memberDetails), HttpStatus.OK);
            return new ResponseEntity<>(memberService.findUserInfo(memberDetails), HttpStatus.OK);
        }
        catch (Exception e){
            //return new ResponseEntity<>(memberService.findUserInfo(memberDetails), HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(memberService.findUserInfo(memberDetails), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "유저 색깔 테마 구매 api", description = "유저 색깔 테마 구매, 저장")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = UserThemeRequestDto.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PostMapping(value = "/user/theme", headers = { "Content-type=application/json" })
    public ResponseEntity<?> updateUserTheme(@Parameter(hidden=true) @AuthenticationPrincipal MemberDetails memberDetails, @RequestBody UserThemeRequestDto userThemeRequestDto) {
        try {
            return new ResponseEntity<>(memberService.updateTheme(memberDetails, userThemeRequestDto), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(memberService.updateTheme(memberDetails, userThemeRequestDto), HttpStatus.BAD_REQUEST);
        }
    }
}
