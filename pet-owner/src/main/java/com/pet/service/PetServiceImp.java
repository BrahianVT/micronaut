package com.pet.service;

import com.pet.domain.Pet;
import com.pet.repository.PetRepository;
import com.pet.service.dto.PetDTO;
import com.pet.service.mapper.PetMapper;
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
public class PetServiceImp  implements PetService {
    private final Logger log = LoggerFactory.getLogger(OwnerServiceImp.class);
    private final PetRepository petRepository;
    private final PetMapper petMapper;

    public PetServiceImp(PetRepository petRepository, PetMapper petMapper){
        this.petRepository = petRepository;
        this.petMapper = petMapper;
    }

    @Override
    public PetDTO save(PetDTO petDTO) {
        log.debug("Save pet ");
        Pet pet = petMapper.toEntity(petDTO);
        pet = petRepository.mergeAndSave(pet);
        return petMapper.toDto(pet);
    }

    @Override
    @ReadOnly
    @Transactional
    public Page<PetDTO> findAll(Pageable pageable) {
        log.debug("Get all pets");
        return petRepository.findAll(pageable).map(petMapper::toDto);
    }

    @Override
    @ReadOnly
    @Transactional
    public Optional<PetDTO> findOne(Long id) {
        log.debug("Get one pet: " + id);
        return  petRepository.findById(id).map(petMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Delete Pet " + id);
        petRepository.deleteById(id);
    }
}
