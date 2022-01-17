package com.pet.service.mapper;

import com.pet.domain.Owner;
import com.pet.service.dto.OwnerDTO;

import javax.inject.Singleton;
import java.util.List;

@Singleton
public class OwnerMapperImp  implements OwnerMapper {

    @Override
    public Owner toEntity(OwnerDTO dto) {
        Owner owner = new Owner();
        owner.setId(dto.getId());
        owner.setTelephone(dto.getTelephone());
        owner.setAddress(dto.getAddress());
        owner.setCity(dto.getCity());
        owner.setLastName(dto.getLastName());
        owner.setFirstName(dto.getFirstNam());
        return owner;
    }
    @Override
    public List<Owner> toEntity(List<OwnerDTO> dtoList) {
        return null;
    }

    @Override
    public List<OwnerDTO> toDto(List<Owner> entityList) {
        return null;
    }
}
