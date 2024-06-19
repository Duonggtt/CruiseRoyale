package com.example.cruiseroyalebe.controller;

import com.example.cruiseroyalebe.entity.CabinTypeImage;
import com.example.cruiseroyalebe.entity.CruiseImage;
import com.example.cruiseroyalebe.service.CabinTypeImageService;
import com.example.cruiseroyalebe.service.impl.CabinTypeImageServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:3000"})
@RequestMapping("/api/cabin-type-images")
public class CabinTypeImageController {
    private final CabinTypeImageServiceImpl cabinTypeImageService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        return ResponseEntity.ok(cabinTypeImageService.uploadFile(file));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> readFile(@PathVariable Integer id) {
        CabinTypeImage image = cabinTypeImageService.getFileById(id);
        // Code logic
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(image.getType()))
                .body(image.getData()); // 200
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFile(@PathVariable Integer id) {
        cabinTypeImageService.deleteFile(id);
        // Code logic
        return ResponseEntity.noContent().build(); // 204
    }
}
