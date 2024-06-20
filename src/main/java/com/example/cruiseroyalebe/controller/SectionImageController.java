package com.example.cruiseroyalebe.controller;

import com.example.cruiseroyalebe.entity.CabinTypeImage;
import com.example.cruiseroyalebe.entity.CruiseDtSectionImage;
import com.example.cruiseroyalebe.service.impl.CruiseDtSectionImgServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:3000"})
@RequestMapping("/api/secImages")
public class SectionImageController {

    private final CruiseDtSectionImgServiceImpl sectionImgService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        return ResponseEntity.ok(sectionImgService.uploadFile(file));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> readFile(@PathVariable Integer id) {
        CruiseDtSectionImage image = sectionImgService.getFileById(id);
        // Code logic
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(image.getType()))
                .body(image.getData()); // 200
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFile(@PathVariable Integer id) {
        sectionImgService.deleteFile(id);
        // Code logic
        return ResponseEntity.noContent().build(); // 204
    }
}
