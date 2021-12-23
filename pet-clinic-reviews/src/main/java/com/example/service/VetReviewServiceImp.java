package com.example.service;

import com.example.domain.VetReview;
import com.example.repository.VetReviewRepository;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Singleton
public class VetReviewServiceImp  implements VetReviewService {
    private final Logger log = LoggerFactory.getLogger(VetReviewServiceImp.class);
    private final VetReviewRepository vetReviewRepository;

    public VetReviewServiceImp(VetReviewRepository vetReviewRepository) {
        this.vetReviewRepository = vetReviewRepository;
    }

    @Override
    public List<VetReview> findAll() {
        log.debug("VetReview findAll");
        return vetReviewRepository.findAll();
    }

    @Override
    public VetReview findByReviewId(String reviewId) {
        log.debug("VetReview finfByReviewId " + reviewId);
        return vetReviewRepository.findByReviewId(reviewId);
    }

    @Override
    public VetReview save(VetReview vetReview) {
        log.debug("VetReview save :" + vetReview);
        return vetReviewRepository.save(vetReview);
    }

    @Override
    public void delete(String reviewId) {
        log.debug("VetReview delete : " + reviewId);
        vetReviewRepository.delete(reviewId);
    }
}
