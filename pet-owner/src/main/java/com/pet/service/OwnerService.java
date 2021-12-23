package com.pet.service;

import com.pet.service.dto.OwnerDTO;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;

import java.util.Optional;

public interface OwnerService {
    OwnerDTO save (OwnerDTO owner);
    Page<OwnerDTO> findAll(Pageable pageable);
    Optional<OwnerDTO> findOne(Long id);
    void delete(Long id);
}
