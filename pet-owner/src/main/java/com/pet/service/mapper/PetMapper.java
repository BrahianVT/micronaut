package com.pet.service.mapper;

import com.pet.domain.Pet;
import com.pet.service.dto.PetDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "jsr330", uses = {PetTypeMapper.class , VisitMapper.class })
public interface PetMapper  extends EntityMapper<PetDTO, Pet> {
    default Pet fromId(Long id){
        if(id == null) return null;

        Pet pet = new Pet();
        pet.setId(id);
        return pet;
    }
}
