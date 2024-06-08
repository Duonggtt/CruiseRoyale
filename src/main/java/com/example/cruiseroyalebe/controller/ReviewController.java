package com.example.cruiseroyalebe.controller;

import com.example.cruiseroyalebe.entity.Owner;
import com.example.cruiseroyalebe.modal.request.UpsertReviewRequest;
import com.example.cruiseroyalebe.service.OwnerService;
import com.example.cruiseroyalebe.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:3000"})
@RequestMapping("/api/reviews")
public class ReviewController {
    public final ReviewService reviewService;

    @GetMapping("/")
    public ResponseEntity<?> getAllReviews(@RequestParam(required = false, defaultValue = "id") String sortField,
                                          @RequestParam(required = false, defaultValue = "esc") String sortDirection,
                                          @RequestParam(required = false, defaultValue = "1") Integer page,
                                          @RequestParam(required = false, defaultValue = "10") Integer limit) {
        return ResponseEntity.ok(reviewService.getAllReviews(page, limit , sortField, sortDirection));
    }

    @PostMapping("/create")
    public ResponseEntity<?> createReview(@Valid @RequestBody UpsertReviewRequest request) {
        return new ResponseEntity<>(reviewService.createReview(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOwner(@PathVariable Integer id,@Valid @RequestBody UpsertReviewRequest request) {
        return new ResponseEntity<>(reviewService.updateReview(id,request), HttpStatus.CREATED);
    }


    @DeleteMapping("/{id}")
    public void deleteRule(@PathVariable Integer id) {
        reviewService.deleteReview(id);
    }

}
