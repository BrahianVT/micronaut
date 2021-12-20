package com.pet.service;


import com.pet.domain.Owner;
import com.pet.repository.OwnerRepository;
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
public class OwnerServiceImp implements OwnerService{
    private final Logger log = LoggerFactory.getLogger(OwnerServiceImp.class);
    private final OwnerRepository ownerRepository;

    public OwnerServiceImp(OwnerRepository ownerRepository){
        this.ownerRepository = ownerRepository;
    }
    @Override
    public Owner save(Owner owner) {
        log.debug("Request to save Owner :" + owner);

        return ownerRepository.mergeAnSave(owner);
    }

    @Override
    @ReadOnly
    @Transactional
    public Page<Owner> findAll(Pageable pageable) {
        log.debug("Request get all owners");
        return ownerRepository.findAll(pageable);
    }

    @Override
    @ReadOnly
    @Transactional
    public Optional<Owner> findOne(Long id) {
        log.debug("Request to get Owener : " + id);
        return ownerRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete owner :" + id);
        ownerRepository.deleteById(id);
    }
}
