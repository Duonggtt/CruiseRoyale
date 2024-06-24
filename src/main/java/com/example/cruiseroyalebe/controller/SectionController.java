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
    private final CruiseDetailSectionServiceImpl cruiseDtSectionService;

    @GetMapping("/")
    public ResponseEntity<?> getAllCruiseDtSectionImages() {
        return ResponseEntity.ok(cruiseDtSectionService.getAllCruiseDetailSections());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCruiseDtSectionImageById(@PathVariable Integer id) {
        return ResponseEntity.ok(cruiseDtSectionService.getCruiseDetailSectionById(id));
    }

    @GetMapping("/by-cruise")
    public ResponseEntity<?> getAllSectionsByCruiseId(@RequestParam Integer cruiseId) {
        return ResponseEntity.ok(cruiseDtSectionService.getAllSectionsByCruiseId(cruiseId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCruiseDtSectionImage(@PathVariable Integer id) {
        cruiseDtSectionService.deleteCruiseDetailSection(id);
        return ResponseEntity.ok("CruiseDtSectionImage deleted successfully");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCruiseDtSectionImage(@PathVariable Integer id, @RequestBody UpsertCruiseDetailSection request) {
        return ResponseEntity.ok(cruiseDtSectionService.updateCruiseDetailSection(id, request));
    }

    @PostMapping("/create")
    public ResponseEntity<?> createCruiseDtSectionImage(@RequestBody UpsertCruiseDetailSection request) {
        return ResponseEntity.ok(cruiseDtSectionService.saveCruiseDetailSection(request));
    }

}
