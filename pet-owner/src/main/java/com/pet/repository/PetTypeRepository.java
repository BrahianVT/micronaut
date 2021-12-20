package com.pet.repository;

import com.pet.domain.PetType;
import io.micronaut.data.jpa.repository.JpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

public abstract class PetTypeRepository implements JpaRepository<PetType, Long> {

    @PersistenceContext
    private final EntityManager entityManager;
    public PetTypeRepository(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Transactional
    public PetType mergeAnSave(PetType petType){
        petType = entityManager.merge(petType);
        return save(petType);
    }
}
