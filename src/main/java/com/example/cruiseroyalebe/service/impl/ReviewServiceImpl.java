package com.example.cruiseroyalebe.service.impl;

import com.example.cruiseroyalebe.entity.Owner;
import com.example.cruiseroyalebe.entity.Review;
import com.example.cruiseroyalebe.exception.NotFoundException;
import com.example.cruiseroyalebe.modal.request.UpsertReviewRequest;
import com.example.cruiseroyalebe.repository.ReviewRepository;
import com.example.cruiseroyalebe.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    @Override
    public Page<Review> getAllReviews(Integer page, Integer limit, String sortField, String sortDirection) {
        Sort sort = Sort.by(sortDirection.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, sortField);
        PageRequest pageRequest = PageRequest.of(page - 1, limit, sort);
        return reviewRepository.findAll(pageRequest);
    }

    @Override
    public Page<Review> findPaginated(Integer page, Integer limit, String sortField, String sortDirection, String keyword) {
        Sort sort = buildSort(sortField, sortDirection);

        Pageable pageable = PageRequest.of(page - 1, limit, sort);
        Page<Review> reviewPage;
        if(keyword != null) {
            reviewPage = reviewRepository.findAll(keyword,pageable);
        }else {
            reviewPage = reviewRepository.findAll(pageable);
        }

        List<Review> reviewList = reviewPage.getContent();

        return new PageImpl<>(reviewList, pageable, reviewPage.getTotalElements());
    }

    @Override
    public Sort buildSort(String sortField, String sortDirection) {
        return sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
    }

    @Override
    public Review createReview(UpsertReviewRequest request) {
        Review review = new Review();
        review.setReviewDate(new Date());
        review.setComment(request.getComment());
        review.setRating(request.getRating());
        reviewRepository.save(review);
        return review;
    }

    @Override
    public Review updateReview(Integer id, UpsertReviewRequest request) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Review not found"));
        review.setComment(request.getComment());
        reviewRepository.save(review);
        return review;
    }

    @Override
    public void deleteReview(Integer id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Review not found"));
        reviewRepository.delete(review);
    }
}
