package com.pet.service;


import com.pet.domain.Owner;
import com.pet.repository.OwnerRepository;
import com.pet.service.dto.OwnerDTO;
import com.pet.service.mapper.OwnerMapper;
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
    private final OwnerMapper ownerMapper;
    public OwnerServiceImp(OwnerRepository ownerRepository, OwnerMapper ownerMapper){
        this.ownerRepository = ownerRepository;
        this.ownerMapper = ownerMapper;
    }
    @Override
    public OwnerDTO save(OwnerDTO ownerDTO) {
        log.debug("Request to save Owner :" + ownerDTO);
        Owner owner = ownerMapper.toEntity(ownerDTO);
        owner = ownerRepository.mergeAnSave(owner);
        return ownerMapper.toDto(owner);
    }

    @Override
    @ReadOnly
    @Transactional
    public Page<OwnerDTO> findAll(Pageable pageable) {
        log.debug("Request get all owners");
        return ownerRepository.findAll(pageable).map(ownerMapper::toDto);
    }

    @Override
    @ReadOnly
    @Transactional
    public Optional<OwnerDTO> findOne(Long id) {
        log.debug("Request to get Owener : " + id);
        return ownerRepository.findById(id).map(ownerMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete owner :" + id);
        ownerRepository.deleteById(id);
    }
}
