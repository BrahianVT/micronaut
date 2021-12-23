package com.pet.service;

import com.pet.service.dto.VisitDTO;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;

import java.util.Optional;

public interface VisitService {

    VisitDTO save (VisitDTO Visit);
    Page<VisitDTO> findAll(Pageable pageable);
    Optional<VisitDTO> findOne(Long id);
    void delete(Long id);
}
