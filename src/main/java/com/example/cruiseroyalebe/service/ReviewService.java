package com.example.cruiseroyalebe.service;

import com.example.cruiseroyalebe.entity.Owner;
import com.example.cruiseroyalebe.entity.Review;
import com.example.cruiseroyalebe.modal.request.UpsertReviewRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface ReviewService {
    Page<Review> getAllReviews(Integer page, Integer limit , String sortField, String sortDirection);
    Page<Review> findPaginated(Integer page, Integer limit, String sortField, String sortDirection, String keyword);
    Sort buildSort(String sortField, String sortDirection);
    Review createReview(UpsertReviewRequest request);
    Review updateReview(Integer id, UpsertReviewRequest request);
    void deleteReview(Integer id);
}
