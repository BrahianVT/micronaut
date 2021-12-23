package com.example.config;

import io.micronaut.context.annotation.Value;

public class MongoConfiguration {
    @Value("${mongodb.databaseName}")
    private String databaseName;

    @Value("${mongodb.collectionName}")
    private String collectionName;

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }
}
