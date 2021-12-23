package com.pet.service.mapper;

import com.pet.domain.Owner;
import com.pet.service.dto.OwnerDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "jsr330", uses = {PetMapper.class})
public interface OwnerMapper extends EntityMapper<OwnerDTO, Owner>{
    default Owner fromId(Long id) {
        if(id == null) return null;

        Owner owner = new Owner();
        owner.setId(id);
        return owner;
    }
}
