package com.example.cruiseroyalebe.controller;

import com.example.cruiseroyalebe.modal.request.UpsertCruiseDetailSection;
import com.example.cruiseroyalebe.service.impl.CruiseDetailSectionServiceImpl;
import com.example.cruiseroyalebe.service.impl.CruiseDtSectionImgServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:3000"})
@RequestMapping("/api/sections")
public class SectionController {
    private final CruiseDetailSectionServiceImpl cruiseDtSectionImgService;

    @GetMapping("/")
    public ResponseEntity<?> getAllCruiseDtSectionImages() {
        return ResponseEntity.ok(cruiseDtSectionImgService.getAllCruiseDetailSections());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCruiseDtSectionImageById(@PathVariable Integer id) {
        return ResponseEntity.ok(cruiseDtSectionImgService.getCruiseDetailSectionById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCruiseDtSectionImage(@PathVariable Integer id) {
        cruiseDtSectionImgService.deleteCruiseDetailSection(id);
        return ResponseEntity.ok("CruiseDtSectionImage deleted successfully");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCruiseDtSectionImage(@PathVariable Integer id, @RequestBody UpsertCruiseDetailSection request) {
        return ResponseEntity.ok(cruiseDtSectionImgService.updateCruiseDetailSection(id, request));
    }

    @PostMapping("/create")
    public ResponseEntity<?> createCruiseDtSectionImage(@RequestBody UpsertCruiseDetailSection request) {
        return ResponseEntity.ok(cruiseDtSectionImgService.saveCruiseDetailSection(request));
    }

}
