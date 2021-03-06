package com.example.service;

import com.example.domain.VetReview;

import java.util.List;

public interface VetReviewService {
    List<VetReview> findAll();
    VetReview findByReviewId(String reviewId);
    VetReview save(VetReview vetReview);
    void delete(String reviewId);
}
