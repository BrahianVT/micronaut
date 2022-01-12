package com.example.utils;

import com.example.domain.VetReview;
import com.example.service.VetReviewService;
import com.mongodb.client.MongoClient;
import io.micronaut.context.annotation.Requires;
import io.micronaut.core.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Singleton
@Requires(beans = MongoClient.class)
public class PetClinicReviewCliClient {

    private final Logger log = LoggerFactory.getLogger(PetClinicReviewCliClient.class);

    private final VetReviewService vetReviewService;

    public PetClinicReviewCliClient(VetReviewService vetReviewService) {
        this.vetReviewService = vetReviewService;
    }

    public void performDatabaseOperations(){
        performFindAll();
        VetReview vetReview = performSave();
        performFindByReviewId(vetReview.getReviewId());
        performDelete(vetReview);
        performFindAll();
    }

    public void performFindAll(){
        log.info("--------------------------------------------");
        log.info("-Request performFindAll-- ");
        log.info("--------------------------------------------");

        List<VetReview> vetReviews = this.vetReviewService.findAll();
        if(CollectionUtils.isNotEmpty(vetReviews)){
            vetReviews.forEach(vetReview -> {
                log.info("Review :" + vetReview);
            });
        }
    }

    protected void performFindByReviewId(String reviewId){
        log.info("--------------------------------------------");
        log.info("-Request performFindByReviewId-- " + reviewId);
        log.info("--------------------------------------------");

        VetReview vetReview = vetReviewService.findByReviewId(reviewId);
        log.info("Review :"  + vetReview);
    }

    protected VetReview performSave(){
        VetReview vetReview = new VetReview(UUID.randomUUID().toString(), "Good experience",1L,3.5, LocalDate.now());

        log.info("--------------------------------------------");
        log.info("-Request performSave -- " + vetReview);
        log.info("--------------------------------------------");
        return vetReviewService.save(vetReview);
    }

    protected void performDelete(VetReview vetReview){
        log.info("--------------------------------------------");
        log.info("-Request performDelete-- " + vetReview);
        log.info("--------------------------------------------");
        vetReviewService.delete(vetReview.getReviewId());
    }
}
