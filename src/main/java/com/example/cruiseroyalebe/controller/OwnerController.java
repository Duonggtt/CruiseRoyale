package com.example.cruiseroyalebe.controller;

import com.example.cruiseroyalebe.entity.Owner;
import com.example.cruiseroyalebe.entity.Rule;
import com.example.cruiseroyalebe.service.OwnerService;
import com.example.cruiseroyalebe.service.RuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:3000"})
@RequestMapping("/api/owners")
public class OwnerController {
    public final OwnerService ownerService;

    @GetMapping("/")
    public ResponseEntity<?> getAllOwners(@RequestParam(required = false, defaultValue = "id") String sortField,
                                         @RequestParam(required = false, defaultValue = "esc") String sortDirection,
                                         @RequestParam(required = false, defaultValue = "1") Integer page,
                                         @RequestParam(required = false, defaultValue = "10") Integer limit) {
        return ResponseEntity.ok(ownerService.getAllOwners(page, limit , sortField, sortDirection));
    }

    @PostMapping("/create")
    public ResponseEntity<?> createOwner(@Valid @RequestBody Owner request) {
        return new ResponseEntity<>(ownerService.createOwner(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOwner(@PathVariable Integer id,@Valid @RequestBody Owner request) {
        return new ResponseEntity<>(ownerService.updateOwner(id,request), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOwnerById(@PathVariable Integer id) {
        return ResponseEntity.ok(ownerService.getOwnerById(id));
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> getOwnerDtoById(@PathVariable Integer id) {
        return ResponseEntity.ok(ownerService.getOwnerDetailById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<?> getOwnerById(@RequestParam String name) {
        return ResponseEntity.ok(ownerService.findOwnersByNameLike(name));
    }

    @GetMapping("/auth")
    public ResponseEntity<?> getOwners() {
        return ResponseEntity.ok(ownerService.getOwners());
    }

    @DeleteMapping("/{id}")
    public void deleteRule(@PathVariable Integer id) {
        ownerService.deleteOwner(id);
    }
}
