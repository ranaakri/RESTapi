package com.example.rest_api_assignment.controller;

import com.example.rest_api_assignment.dtos.response.EmployeeResDto;
import com.example.rest_api_assignment.services.EmployeeServices;
import com.example.rest_api_assignment.services.ImageService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.print.attribute.standard.Media;
import java.io.IOException;
import java.nio.file.Files;

@RestController
@RequestMapping("/api/image")
public class ImageUploadController {

    private final ImageService imageService;

    @Autowired
    public ImageUploadController(ImageService imageService){
        this.imageService = imageService;
    }

    @PostMapping("/{id}")
    public ResponseEntity<EmployeeResDto> uploadImage(@PathVariable long id, @RequestParam MultipartFile file) throws IOException {
        return new ResponseEntity<>(imageService.upload(id, file),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> get(@PathVariable long id) throws IOException{
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(imageService.getProfile(id));
    }
}
