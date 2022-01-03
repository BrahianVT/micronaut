package com.pet.service.mapper;

import com.pet.domain.Owner;
import com.pet.domain.Pet;
import com.pet.service.OwnerServiceImp;
import com.pet.service.dto.OwnerDTO;
import com.pet.service.dto.PetDTO;
import org.mapstruct.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

@Mapper(componentModel = "jsr330", uses = {PetMapper.class})
public interface OwnerMapper extends EntityMapper<OwnerDTO, Owner>{
     final Logger log = LoggerFactory.getLogger(OwnerMapper.class);
    default Owner fromId(Long id) {
        if(id == null) return null;

        Owner owner = new Owner();
        owner.setId(id);
        return owner;
    }
    default OwnerDTO toDto(Owner owner){

        OwnerDTO dto = new OwnerDTO();
        dto.setId(owner.getId());
        dto.setFirstNam(owner.getFirstName());
        dto.setLastName(owner.getLastName());
        dto.setAddress(owner.getAddress());
        Set<PetDTO> pets = new HashSet<>();
        for(Pet p: owner.getPets()){
            PetDTO pt = new PetDTO();
            pt.setId(p.getId());
            pt.setName(p.getName());
            pt.setBirthDate(p.getBirthDate());
            pets.add(pt);
        }
        dto.setPets(pets);
        return dto;
    }
}
