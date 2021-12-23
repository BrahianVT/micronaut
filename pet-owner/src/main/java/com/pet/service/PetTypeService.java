package com.pet.service;

import com.pet.service.dto.PetTypeDTO;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;

import java.util.Optional;

public interface PetTypeService {
    PetTypeDTO save (PetTypeDTO petType);
    Page<PetTypeDTO> findAll(Pageable pageable);
    Optional<PetTypeDTO> findOne(Long id);
    void delete(Long id);
}
