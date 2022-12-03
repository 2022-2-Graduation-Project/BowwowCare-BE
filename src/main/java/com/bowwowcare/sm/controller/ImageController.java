package com.bowwowcare.sm.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Objects;


@Tag(name = "picture", description = "사진 API")
@RequestMapping("http://43.200.174.33/api")
@RequiredArgsConstructor
@RestController
@CrossOrigin
public class ImageController {


    @PostMapping("/v1/predict")
    @ResponseBody
    public void FileUpload(MultipartFile file, Model model) {

        try {

            if(!file.isEmpty()) {
                file.transferTo(new File(Objects.requireNonNull(file.getOriginalFilename())));
            }


        }
        catch (Exception e ) {
            e.printStackTrace();
        }
    }
}
