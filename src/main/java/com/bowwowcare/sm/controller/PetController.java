package com.bowwowcare.sm.controller;

import com.bowwowcare.sm.config.security.member.MemberDetails;
import com.bowwowcare.sm.dto.pet.PetInfoResponseDto;
import com.bowwowcare.sm.dto.pet.PetRegisterRequestDto;
import com.bowwowcare.sm.dto.pet.PetRegisterResponseDto;
import com.bowwowcare.sm.service.PetService;
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

@Tag(name = "pet", description = "반려동물 관련 API")
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@RestController
@CrossOrigin
public class PetController {

    private final PetService petService;

    @Operation(summary = "반려동물 등록 api", description = "반려동물 등록")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = PetRegisterRequestDto.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PostMapping("/pets")
    public PetRegisterResponseDto registerPet(@Parameter(hidden=true) @AuthenticationPrincipal MemberDetails memberDetails, @RequestBody PetRegisterRequestDto petRegisterRequestDto) {
        PetRegisterResponseDto responseDto = petService.registerPet(petRegisterRequestDto, memberDetails);
        return responseDto;
    }


    @Operation(summary = "반려동물 목록 조회 api", description = "한 유저가 등록한 자신의 반려동물 목록 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping("/pets")
    public ResponseEntity<?> findPetList(@Parameter(hidden=true) @AuthenticationPrincipal MemberDetails memberDetails) {
        try {
            return new ResponseEntity<>(petService.findPetListByMember(memberDetails), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(petService.findPetListByMember(memberDetails), HttpStatus.BAD_REQUEST);
        }
    }


    @Operation(summary = "반려동물 상세 정보 조회 api", description = "반려동물 상세 정보 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping("/pets/{petId}")
    public PetInfoResponseDto infoPet(@Parameter(hidden=true) @AuthenticationPrincipal MemberDetails memberDetails, @PathVariable("petId") int petId) {
        PetInfoResponseDto responseDto = petService.findPetById(petId);
        return responseDto;
    }
}
