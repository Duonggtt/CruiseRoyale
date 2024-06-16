package com.example.cruiseroyalebe.controller;

import com.example.cruiseroyalebe.entity.Cruise;
import com.example.cruiseroyalebe.entity.Location;
import com.example.cruiseroyalebe.modal.request.UpsertCruiseRequest;
import com.example.cruiseroyalebe.service.CruiseService;
import com.example.cruiseroyalebe.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:3000"})
    @RequestMapping("/api/cruises")
public class CruiseController {
    public final CruiseService cruiseService;

    @GetMapping("")
    public ResponseEntity<?> getAllCruisesPagination(@RequestParam(required = false, defaultValue = "id") String sortField,
                                             @RequestParam(required = false, defaultValue = "esc") String sortDirection,
                                             @RequestParam(required = false, defaultValue = "1") Integer page,
                                             @RequestParam(required = false, defaultValue = "10") Integer limit) {
        return ResponseEntity.ok(cruiseService.getAllCruises(page, limit , sortField, sortDirection));
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllCruises() {
        return ResponseEntity.ok(cruiseService.getAllCruises());
    }
    @GetMapping("/auth")
    public ResponseEntity<?> getAllCruisesAuth() {
        return ResponseEntity.ok(cruiseService.getAllCruises());
    }

    @PostMapping("/create")
    public ResponseEntity<?> createCruise(@Valid @RequestBody UpsertCruiseRequest request) {
        return new ResponseEntity<>(cruiseService.createCruise(request), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCruise(@PathVariable Integer id,@Valid @RequestBody UpsertCruiseRequest request) {
        return new ResponseEntity<>(cruiseService.updateCruise(id,request), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCruiseById(@PathVariable Integer id) {
        return ResponseEntity.ok(cruiseService.getCruiseById(id));
    }

    @GetMapping("/auth/{id}")
    public ResponseEntity<?> getCruiseByIdAuth(@PathVariable Integer id) {
        return ResponseEntity.ok(cruiseService.getCruiseById(id));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCruise(@PathVariable Integer id) {
        cruiseService.deleteCruise(id);
    }

    @GetMapping("/filter-by-price")
    public ResponseEntity<?> getCruisesByPriceRange(@RequestParam int priceRange) {
        List<Cruise> cruises = cruiseService.findCruisesByPriceRange(priceRange);
        if(cruises.isEmpty()) {
            return ResponseEntity.ok("Không tìm thấy tàu nào trong khoảng giá này");
        }
        return ResponseEntity.ok(cruiseService.findCruisesByPriceRange(priceRange));
    }

    @GetMapping("/featured")
    public ResponseEntity<?> getSomeFeaturedCruise() {
        return ResponseEntity.ok(cruiseService.getSomeFeaturedCruise());
    }

    @GetMapping("/filter/locate/{locationId}")
    public ResponseEntity<?> getCruisesByLocationId(@PathVariable Integer locationId) {
        return ResponseEntity.ok(cruiseService.getCruisesByLocationId(locationId));
    }

    @GetMapping("/filter")
    public ResponseEntity<?> getCruisesByNameLike(@RequestParam String name) {
        List<Cruise> cruises = cruiseService.getCruisesByNameLike(name);
        if(cruises.isEmpty()) {
            return ResponseEntity.ok("Không tìm thấy tàu nào chứa từ khóa: " + name);
        }
        return ResponseEntity.ok(cruiseService.getCruisesByNameLike(name));
    }


}
