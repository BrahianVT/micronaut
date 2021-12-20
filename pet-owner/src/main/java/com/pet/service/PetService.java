package com.pet.service;

import com.pet.domain.Pet;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;

import java.util.Optional;

public interface PetService {
    Pet save (Pet Pet);
    Page<Pet> findAll(Pageable pageable);
    Optional<Pet> findOne(Long id);
    void delete(Long id);
}
