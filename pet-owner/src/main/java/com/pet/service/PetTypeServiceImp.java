package com.pet.service;

import com.pet.domain.PetType;
import com.pet.repository.PetTypeRepository;
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
public class PetTypeServiceImp  implements PetTypeService {

    private final Logger log = LoggerFactory.getLogger(PetTypeServiceImp.class);
    private final PetTypeRepository petTypeRepository;

    public PetTypeServiceImp(PetTypeRepository petTypeRepository){
        this.petTypeRepository = petTypeRepository;
    }

    @Override
    public PetType save(PetType petType) {
        log.debug(" Save PetType");
        return petTypeRepository.mergeAnSave(petType);
    }

    @Override
    @ReadOnly
    @Transactional
    public Page<PetType> findAll(Pageable pageable) {
        log.debug("Find all PetType");
        return petTypeRepository.findAll(pageable);
    }

    @Override
    @ReadOnly
    @Transactional
    public Optional<PetType> findOne(Long id) {
        log.debug("Find one petType" + id);
        return petTypeRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Delete PetType");
        petTypeRepository.deleteById(id);
    }
}
