package com.example.cruiseroyalebe.controller;

import com.example.cruiseroyalebe.entity.Rule;
import com.example.cruiseroyalebe.entity.Tag;
import com.example.cruiseroyalebe.service.RuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/rules")
public class RuleController {
    public final RuleService ruleService;

    @GetMapping("/")
    public ResponseEntity<?> getAllRules(@RequestParam(required = false, defaultValue = "id") String sortField,
                                        @RequestParam(required = false, defaultValue = "esc") String sortDirection,
                                        @RequestParam(required = false, defaultValue = "1") Integer page,
                                        @RequestParam(required = false, defaultValue = "10") Integer limit) {
        return ResponseEntity.ok(ruleService.getAllRules(page, limit , sortField, sortDirection));
    }

    @PostMapping("/create")
    public ResponseEntity<?> createRule(@Valid @RequestBody Rule request) {
        return new ResponseEntity<>(ruleService.createRule(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRule(@PathVariable Integer id,@Valid @RequestBody Rule request) {
        return new ResponseEntity<>(ruleService.updateRule(id,request), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRuleById(@PathVariable Integer id) {
        return ResponseEntity.ok(ruleService.getRuleById(id));
    }

    @DeleteMapping("/{id}")
    public void deleteRule(@PathVariable Integer id) {
        ruleService.deleteRule(id);
    }
}
