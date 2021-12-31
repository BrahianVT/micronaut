package com.pet.service;

import com.pet.domain.PetType;
import com.pet.repository.PetTypeRepository;
import com.pet.service.dto.PetTypeDTO;
import com.pet.service.mapper.PetTypeMapper;
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
public class PetTypeServiceImp  implements PetTypeService {

    private final Logger log = LoggerFactory.getLogger(PetTypeServiceImp.class);
    private final PetTypeRepository petTypeRepository;
    private final PetTypeMapper petTypeMapper;

    public PetTypeServiceImp(PetTypeRepository petTypeRepository, PetTypeMapper petTypeMapper){
        this.petTypeMapper = petTypeMapper;
        this.petTypeRepository = petTypeRepository;
    }

    @Override
    public PetTypeDTO save(PetTypeDTO petTypeDto) {
        PetType petType = petTypeMapper.toEntity(petTypeDto);
        petType = petTypeRepository.mergeAnSave(petType);
        log.debug(" Save PetType");
        return petTypeMapper.toDto(petType);
    }

    @Override
    @ReadOnly
    @Transactional
    public Page<PetTypeDTO> findAll(Pageable pageable) {
        log.debug("Find all PetType");
        return petTypeRepository.findAll(pageable).map(petTypeMapper::toDto);
    }

    @Override
    @ReadOnly
    @Transactional
    public Optional<PetTypeDTO> findOne(Long id) {
        log.debug("Find one petType" + id);
        return petTypeRepository.findById(id).map(petTypeMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Delete PetType");
        petTypeRepository.deleteById(id);
    }
}
