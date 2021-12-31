package com.pet.service;


import com.pet.domain.Visit;
import com.pet.repository.VisitRepository;
import com.pet.service.dto.VisitDTO;
import com.pet.service.mapper.OwnerMapper;
import com.pet.service.mapper.VisitMapper;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.transaction.annotation.ReadOnly;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import javax.transaction.Transactional;
import java.util.Optional;

@Singleton
@Transactional
public class VisitServiceImp  implements  VisitService {

    private final Logger log = LoggerFactory.getLogger(VisitServiceImp.class);
    private VisitRepository visitRepository;
    private final VisitMapper visitMapper;

    public VisitServiceImp (VisitRepository visitRepository, VisitMapper visitMapper){
        this.visitRepository = visitRepository;
        this.visitMapper = visitMapper;
    }
    @Override
    public VisitDTO save(VisitDTO visitDTO) {
        log.debug("Save Visit");
        Visit visit = visitMapper.toEntity(visitDTO);
        visit = visitRepository.mergeAnSave(visit);
        return visitMapper.toDto(visit);
    }

    @Override
    @ReadOnly
    @Transactional
    public Page<VisitDTO> findAll(Pageable pageable) {
        log.debug("Find all visits");
        return visitRepository.findAll(pageable).map(visitMapper::toDto);
    }

    @Override
    public Optional<VisitDTO> findOne(Long id) {
        log.debug("Find One Visit :" + id);
        return visitRepository.findById(id).map(visitMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("delete Visit" + id);
        visitRepository.deleteById(id);
    }
}
