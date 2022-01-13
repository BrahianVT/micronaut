package com.example.rest;

import com.example.domain.VetReview;
import com.example.integration.VetReviewClient;
import com.example.service.VetReviewService;
import com.example.utils.HeaderUtil;
import io.micronaut.context.annotation.Value;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
//import io.micronaut.security.annotation.Secured;
//import io.micronaut.security.rules.SecurityRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@Controller("/api")
public class VetReviewResource {

    private final Logger log = LoggerFactory.getLogger(VetReviewResource.class);

    private static final String ENTITY_NAME = "vetReview";

    @Value("${micronaut.application.name}")
    private String applicationName;

    private final VetReviewService vetReviewService;
    private final VetReviewClient vetReviewClient;

    public VetReviewResource(VetReviewService vetReviewService, VetReviewClient vetReviewClient) {
        this.vetReviewService = vetReviewService;
        this.vetReviewClient = vetReviewClient;
    }

    @Get("/vet-reviews")
    @ExecuteOn(TaskExecutors.IO)
    public HttpResponse<List<VetReview>> getAllVetReviews(HttpRequest request) {
        log.debug("REST request to get a page of VetReviews");
        List<VetReview> list = vetReviewService.findAll();
        return HttpResponse.ok(list);
    }

    @Post("/vet-reviews")
    @ExecuteOn(TaskExecutors.IO)
    public HttpResponse<VetReview> createVetReview(@Body VetReview vetReview) throws URISyntaxException {
        log.debug("REST request to save VetReview : {}", vetReview);

        if (vetReview.getReviewId() != null) {
            throw new RuntimeException("A new vetReview cannot already have an ID " + ENTITY_NAME +  " idexists");
        }

        VetReview result = vetReviewService.save(vetReview);

        vetReviewClient.send(result);

        log.info("The review id : " + result.getReviewId());
        URI location = new URI("/api/vet-reviews/" + result.getReviewId());
        return HttpResponse.created(result).headers(headers -> {
            headers.location(location);
            HeaderUtil.createEntityCreationAlert(headers, applicationName, true, ENTITY_NAME, result.getReviewId());
        });
    }

    @Get("/vet-reviews/{reviewId}")
    @ExecuteOn(TaskExecutors.IO)
    public Optional<VetReview> getVetReview(@PathVariable String reviewId) {
        log.debug("REST request to get VetReview : {}", reviewId);
        return Optional.ofNullable(vetReviewService.findByReviewId(reviewId));
    }

}
