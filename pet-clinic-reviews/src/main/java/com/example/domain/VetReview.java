package com.example.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.time.LocalDate;

public class VetReview {
    private String reviewId, comment;
    private Long vetId;
    private Double rating;
    private LocalDate dateAdded;

    @BsonCreator
    @JsonCreator
    public VetReview(
            @JsonProperty("reviewId") @BsonProperty("reviewId") String reviewId,
            @JsonProperty("comment") @BsonProperty("comment") String comment,
            @JsonProperty("vetId") @BsonProperty("vetId") Long vetId,
            @JsonProperty("rating") @BsonProperty("rating") Double rating,
            @JsonProperty("dateAdded") @BsonProperty("dateAdded")LocalDate dateAdded) {
        this.reviewId = reviewId;
        this.comment = comment;
        this.vetId = vetId;
        this.rating = rating;
        this.dateAdded = dateAdded;
    }

    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getVetId() {
        return vetId;
    }

    public void setVetId(Long vetId) {
        this.vetId = vetId;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public LocalDate getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDate dateAdded) {
        this.dateAdded = dateAdded;
    }

    @Override
    public String toString() {
        return "VetReview{" +
                "reviewId='" + reviewId + '\'' +
                ", comment='" + comment + '\'' +
                ", vetId=" + vetId +
                ", rating=" + rating +
                ", dateAdded=" + dateAdded +
                '}';
    }
}
