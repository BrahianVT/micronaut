package com.example.repository;

import com.example.config.MongoConfiguration;
import com.example.domain.VetReview;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

@Singleton
public class VetReviewRepositoryImp  implements VetReviewRepository {

    private final MongoClient mongoClient;
    private final MongoConfiguration mongoConfiguration;

    public VetReviewRepositoryImp(MongoClient mongoClient, MongoConfiguration mongoConfiguration) {
        this.mongoClient = mongoClient;
        this.mongoConfiguration = mongoConfiguration;
    }

    @Override
    public List<VetReview> findAll() {
        List<VetReview> vetReviews = new ArrayList<>();
        getCollection().find().forEach( vetReview -> {
            vetReviews.add(vetReview);
        });
        return vetReviews;
    }

    @Override
    public VetReview findByReviewId(String reviewID) {
        return getCollection().find(eq("reviewId", reviewID)).first();
    }

    @Override
    public VetReview save(VetReview vetReview) {
        getCollection().insertOne(vetReview).getInsertedId();
        return findByReviewId(vetReview.getReviewId());
    }

    @Override
    public void delete(String reviewId) {
        getCollection().deleteOne(eq("reviewId", reviewId));
    }

    private MongoCollection<VetReview> getCollection() {
        return mongoClient.getDatabase(mongoConfiguration.getDatabaseName())
                .getCollection(mongoConfiguration.getCollectionName(), VetReview.class);
    }
}
