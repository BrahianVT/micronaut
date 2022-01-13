package com.example.integration;

import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.Topic;
import io.micronaut.messaging.annotation.Body;

@KafkaClient
public interface VetReviewClient {
    @Topic("vet-reviews")
    void send(@Body String vetReview);
}
