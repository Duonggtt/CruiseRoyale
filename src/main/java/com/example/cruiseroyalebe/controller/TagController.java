package com.example.cruiseroyalebe.controller;

import com.example.cruiseroyalebe.entity.Tag;
import com.example.cruiseroyalebe.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:3000"})
@RequestMapping("/api/tags")
public class TagController {
    public final TagService tagService;

    @GetMapping("")
    public ResponseEntity<?> getAllTags(@RequestParam(required = false, defaultValue = "id") String sortField,
                                           @RequestParam(required = false, defaultValue = "esc") String sortDirection,
                                           @RequestParam(required = false, defaultValue = "1") Integer page,
                                           @RequestParam(required = false, defaultValue = "10") Integer limit) {
        return ResponseEntity.ok(tagService.getAllTags(page, limit , sortField, sortDirection));
    }

    @PostMapping("/create")
    public ResponseEntity<?> createTag(@Valid @RequestBody Tag request) {
        return new ResponseEntity<>(tagService.createTag(request), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateTag(@PathVariable Integer id,@Valid @RequestBody Tag request) {
        return new ResponseEntity<>(tagService.updateTag(id,request), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTagById(@PathVariable Integer id) {
        return ResponseEntity.ok(tagService.getTagById(id));
    }

    @DeleteMapping("/{id}")
    public void deleteTag(@PathVariable Integer id) {
        tagService.deleteTag(id);
    }

    @GetMapping("/auth/")
    public ResponseEntity<?> getTagsAuth() {
        return ResponseEntity.ok(tagService.getTags());
    }

    @GetMapping("/")
    public ResponseEntity<?> getTags() {
        return ResponseEntity.ok(tagService.getTags());
    }

    @GetMapping("/search")
    public ResponseEntity<?> getTagsByName(@RequestParam String name) {
        return ResponseEntity.ok(tagService.getTagsByName(name));
    }
}
