package com.example.services;

import com.example.domain.Vet;

import java.util.Collection;
import java.util.Optional;

public interface VetService {
    Vet save(Vet vet) throws Exception;
    Collection<Vet> findAll() throws Exception;
    Optional<Vet> findOne(Long id) throws Exception;
    void delete(Long id) throws Exception;
    void updateVetAverageRating(Long vetId, Double rating) throws Exception;
}
