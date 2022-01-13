package com.example.service;

import com.example.domain.VetReview;
import com.example.repository.VetReviewRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import java.util.List;
import java.util.UUID;

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
        if (vetReview.getReviewId() == null) {
            return insertVetReview(vetReview);
        } else {
            return updateVetReview(vetReview);
        }
    }

    private VetReview insertVetReview(VetReview vetReview) {
        vetReview.setReviewId(UUID.randomUUID().toString());
        return vetReviewRepository.save(vetReview);
    }

    private VetReview updateVetReview(VetReview vetReview) {
        return vetReviewRepository.save(vetReview);
    }

    @Override
    public void delete(String reviewId) {
        log.debug("VetReview delete : " + reviewId);
        vetReviewRepository.delete(reviewId);
    }
}
