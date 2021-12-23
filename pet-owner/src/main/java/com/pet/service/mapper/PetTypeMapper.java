package com.pet.service.mapper;

import com.pet.domain.PetType;
import com.pet.service.dto.PetTypeDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "jsr330", uses = {})
public interface PetTypeMapper  extends EntityMapper<PetTypeDTO, PetType> {
    default PetType fromId (Long id){
        if(id == null) return null;

        PetType petType = new PetType();
        petType.setId(id);
        return petType;
    }
}
