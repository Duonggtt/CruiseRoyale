package com.example.cruiseroyalebe.controller;

import com.example.cruiseroyalebe.entity.Location;
import com.example.cruiseroyalebe.modal.request.UpsertCabinRequest;
import com.example.cruiseroyalebe.service.CabinService;
import com.example.cruiseroyalebe.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/cabins")
public class CabinController {
    public final CabinService cabinService;

    @GetMapping("/")
    public ResponseEntity<?> getAllCabins(@RequestParam(required = false, defaultValue = "id") String sortField,
                                             @RequestParam(required = false, defaultValue = "esc") String sortDirection,
                                             @RequestParam(required = false, defaultValue = "1") Integer page,
                                             @RequestParam(required = false, defaultValue = "10") Integer limit) {
        return ResponseEntity.ok(cabinService.getAllCabins(page, limit , sortField, sortDirection));
    }

    @PostMapping("/create")
    public ResponseEntity<?> createCabin(@Valid @RequestBody UpsertCabinRequest request) {
        return new ResponseEntity<>(cabinService.createCabin(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCabin(@PathVariable Integer id,@Valid @RequestBody UpsertCabinRequest request) {
        return new ResponseEntity<>(cabinService.updateCabin(id,request), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCabinById(@PathVariable Integer id) {
        return ResponseEntity.ok(cabinService.getCabinById(id));
    }

    @DeleteMapping("/{id}")
    public void deleteCabin(@PathVariable Integer id) {
        cabinService.deleteCabin(id);
    }
}
