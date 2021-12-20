package com.pet.service;


import com.pet.domain.Visit;
import com.pet.repository.VisitRepository;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.transaction.annotation.ReadOnly;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.transaction.Transactional;
import java.util.Optional;

@Singleton
@Transactional
public class VisitServiceImp  implements  VisitService {

    private final Logger log = LoggerFactory.getLogger(VisitServiceImp.class);
    private VisitRepository visitRepository;

    public VisitServiceImp (VisitRepository visitRepository){
        this.visitRepository = visitRepository;
    }
    @Override
    public Visit save(Visit visit) {
        log.debug("Save Visit");
        return visitRepository.mergeAnSave(visit);
    }

    @Override
    @ReadOnly
    @Transactional
    public Page<Visit> findAll(Pageable pageable) {
        log.debug("Find all visits");
        return visitRepository.findAll(pageable);
    }

    @Override
    public Optional<Visit> findOne(Long id) {
        log.debug("Find One Visit :" + id);
        return visitRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("delete Visit" + id);
        visitRepository.deleteById(id);
    }
}
