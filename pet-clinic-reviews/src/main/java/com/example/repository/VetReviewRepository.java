package com.example.repository;

import com.example.domain.VetReview;

import java.util.List;

public interface VetReviewRepository {
    List<VetReview> findAll();
    VetReview findByReviewId(String reviewID);
    VetReview save(VetReview vetReview);
    void delete(String reviewId);
}
