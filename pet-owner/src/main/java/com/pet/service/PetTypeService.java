package com.pet.service;

import com.pet.domain.PetType;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;

import java.util.Optional;

public interface PetTypeService {
    PetType save (PetType PetType);
    Page<PetType> findAll(Pageable pageable);
    Optional<PetType> findOne(Long id);
    void delete(Long id);
}
