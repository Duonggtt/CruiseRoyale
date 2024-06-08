package com.example.cruiseroyalebe.controller;

import com.example.cruiseroyalebe.entity.Location;
import com.example.cruiseroyalebe.entity.Owner;
import com.example.cruiseroyalebe.service.LocationService;
import com.example.cruiseroyalebe.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:3000"})
@RequestMapping("/api/locations")
public class LocationController {
    public final LocationService locationService;

    @GetMapping("/")
    public ResponseEntity<?> getAllLocations(@RequestParam(required = false, defaultValue = "id") String sortField,
                                          @RequestParam(required = false, defaultValue = "esc") String sortDirection,
                                          @RequestParam(required = false, defaultValue = "1") Integer page,
                                          @RequestParam(required = false, defaultValue = "10") Integer limit) {
        return ResponseEntity.ok(locationService.getAllLocations(page, limit , sortField, sortDirection));
    }

    @PostMapping("/create")
    public ResponseEntity<?> createLocation(@Valid @RequestBody Location request) {
        return new ResponseEntity<>(locationService.createLocation(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateLocation(@PathVariable Integer id,@Valid @RequestBody Location request) {
        return new ResponseEntity<>(locationService.updateLocation(id,request), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getLocationById(@PathVariable Integer id) {
        return ResponseEntity.ok(locationService.getLocationById(id));
    }

    @DeleteMapping("/{id}")
    public void deleteLocation(@PathVariable Integer id) {
        locationService.deleteLocation(id);
    }
}
