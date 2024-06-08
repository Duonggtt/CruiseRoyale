package com.example.cruiseroyalebe.controller;

import com.example.cruiseroyalebe.entity.UserImage;
import com.example.cruiseroyalebe.service.UserImageService;
import com.example.cruiseroyalebe.service.impl.UserImageServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:3000"})
@RequestMapping("/api/images")
public class UserImageController {
    private final UserImageServiceImpl imageService;

    @GetMapping("/")
    public ResponseEntity<?> getFilesOfCurrentUser(@RequestParam Integer userId) {
        return ResponseEntity.ok(imageService.getFilesOfCurrentUser(userId));
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam Integer userId) throws IOException {
        return ResponseEntity.ok(imageService.uploadFile(file, userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> readFile(@PathVariable Integer id) {
        UserImage image = imageService.getFileById(id);
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
