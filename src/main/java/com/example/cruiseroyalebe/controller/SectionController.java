package com.example.cruiseroyalebe.controller;

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

}
