package com.pet.service;

import com.pet.domain.Pet;
import com.pet.repository.PetRepository;
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
public class PetServiceImp  implements PetService {
    private final Logger log = LoggerFactory.getLogger(OwnerServiceImp.class);
    private final PetRepository petRepository;

    public PetServiceImp(PetRepository petRepository){
        this.petRepository = petRepository;
    }

    @Override
    public Pet save(Pet pet) {
        log.debug("Save pet ");
        return petRepository.mergeAndSave(pet);
    }

    @Override
    @ReadOnly
    @Transactional
    public Page<Pet> findAll(Pageable pageable) {
        log.debug("Get all pets");
        return petRepository.findAll(pageable);
    }

    @Override
    @ReadOnly
    @Transactional
    public Optional<Pet> findOne(Long id) {
        log.debug("Get one pet: " + id);
        return  petRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Delete Pet " + id);
        petRepository.deleteById(id);
    }
}
