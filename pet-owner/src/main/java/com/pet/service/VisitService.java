package com.pet.service;

import com.pet.domain.Visit;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;

import java.util.Optional;

public interface VisitService {

    Visit save (Visit Visit);
    Page<Visit> findAll(Pageable pageable);
    Optional<Visit> findOne(Long id);
    void delete(Long id);
}
