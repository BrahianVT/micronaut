package com.example.integration.dto;

import io.micronaut.core.annotation.Introspected;

import java.io.Serializable;
import java.time.LocalDate;

@Introspected
public class VetReviewDTO implements Serializable {
    private String reviewId;

    private Double rating;

    private String comment;

    private Long vetId;

    private LocalDate dateAdded;

    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
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

    public LocalDate getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDate dateAdded) {
        this.dateAdded = dateAdded;
    }

    @Override
    public String toString() {
        return "VetReviewDTO{" +
                "reviewId='" + reviewId + '\'' +
                ", rating=" + rating +
                ", comment='" + comment + '\'' +
                ", vetId=" + vetId +
                ", dateAdded=" + dateAdded +
                '}';
    }
}
