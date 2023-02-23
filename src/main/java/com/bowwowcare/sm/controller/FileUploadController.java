package com.bowwowcare.sm.controller;

import com.bowwowcare.sm.dto.file.FileResponseDto;
import com.bowwowcare.sm.dto.survey.AnxietyRequestDto;
import com.bowwowcare.sm.service.FileUploadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Tag(name = "s3-upload", description = "사진 전송 API")
@RequestMapping(value = "/api/v1")
@RequiredArgsConstructor
@RestController
@CrossOrigin
public class FileUploadController {

    private final FileUploadService fileUploadService;

    @Operation(summary = "사진 업로드 api", description = "사진 업로드 후 사진 url 반환")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = MultipartFile.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PostMapping(value = "/image")
    public FileResponseDto uploadFile(@RequestParam MultipartFile file)
            throws IOException {
        return fileUploadService.saveUploadFile(file);
    }
}
