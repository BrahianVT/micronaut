package com.example.rest;

import com.example.domain.VetReview;
import com.example.service.VetReviewService;
import io.micronaut.context.annotation.Value;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Controller("/api")
public class VetReviewResource {

    private final Logger log = LoggerFactory.getLogger(VetReviewResource.class);

    private static final String ENTITY_NAME = "vetReview";

    @Value("${micronaut.application.name}")
    private String applicationName;

    private final VetReviewService vetReviewService;

    public VetReviewResource(VetReviewService vetReviewService) {
        this.vetReviewService = vetReviewService;
    }

    @Get("/vet-reviews")
    @ExecuteOn(TaskExecutors.IO)
    public HttpResponse<List<VetReview>> getAllVetReviews(HttpRequest request) {
        log.debug("REST request to get a page of VetReviews");
        List<VetReview> list = vetReviewService.findAll();
        return HttpResponse.ok(list);
    }

}
