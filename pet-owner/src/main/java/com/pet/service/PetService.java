package com.pet.service;

import com.pet.service.dto.PetDTO;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;

import java.util.Optional;

public interface PetService {
    PetDTO save (PetDTO Pet);
    Page<PetDTO> findAll(Pageable pageable);
    Optional<PetDTO> findOne(Long id);
    void delete(Long id);
}
