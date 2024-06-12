package com.example.cruiseroyalebe.controller;

import com.example.cruiseroyalebe.entity.CabinType;
import com.example.cruiseroyalebe.entity.Location;
import com.example.cruiseroyalebe.modal.request.UpsertCabinTypeRequest;
import com.example.cruiseroyalebe.service.CabinTypeService;
import com.example.cruiseroyalebe.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:3000"})
@RequestMapping("/api/cabin-types")
public class CabinTypeController {
    public final CabinTypeService cabinTypeService;

    @GetMapping("/")
    public ResponseEntity<?> getAllCabinTypes(@RequestParam(required = false, defaultValue = "id") String sortField,
                                             @RequestParam(required = false, defaultValue = "esc") String sortDirection,
                                             @RequestParam(required = false, defaultValue = "1") Integer page,
                                             @RequestParam(required = false, defaultValue = "10") Integer limit) {
        return ResponseEntity.ok(cabinTypeService.getAllCabinTypes(page, limit , sortField, sortDirection));
    }

    @PostMapping("/create")
    public ResponseEntity<?> createCabinType(@Valid @RequestBody UpsertCabinTypeRequest request) {
        return new ResponseEntity<>(cabinTypeService.createCabinType(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCabinType(@PathVariable Integer id,@Valid @RequestBody UpsertCabinTypeRequest request) {
        return new ResponseEntity<>(cabinTypeService.updateCabinType(id,request), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCabinTypeById(@PathVariable Integer id) {
        return ResponseEntity.ok(cabinTypeService.getCabinTypeById(id));
    }

    @DeleteMapping("/{id}")
    public void deleteCabinType(@PathVariable Integer id) {
        cabinTypeService.deleteCabinType(id);
    }
}
