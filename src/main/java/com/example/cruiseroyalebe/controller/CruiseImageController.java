package com.example.cruiseroyalebe.controller;

import com.example.cruiseroyalebe.entity.CruiseImage;
import com.example.cruiseroyalebe.entity.UserImage;
import com.example.cruiseroyalebe.service.CruiseImageService;
import com.example.cruiseroyalebe.service.impl.CruiseImageServiceImpl;
import com.example.cruiseroyalebe.service.impl.UserImageServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/cruise/images")
public class CruiseImageController {
    private final CruiseImageServiceImpl imageService;

    @GetMapping("/")
    public ResponseEntity<?> getFilesOfCurrentCruise(@RequestParam Integer cruiseId) {
        return ResponseEntity.ok(imageService.getFilesOfCurrentCruise(cruiseId));
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam Integer cruiseId) throws IOException {
        return ResponseEntity.ok(imageService.uploadFile(file, cruiseId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> readFile(@PathVariable Integer id) {
        CruiseImage image = imageService.getFileById(id);
        // Code logic
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(image.getType()))
                .body(image.getData()); // 200
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFile(@PathVariable Integer id) {
        imageService.deleteFile(id);
        // Code logic
        return ResponseEntity.noContent().build(); // 204
    }
}
