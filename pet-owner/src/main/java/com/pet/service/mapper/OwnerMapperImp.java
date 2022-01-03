package com.pet.service.mapper;

import com.pet.domain.Owner;
import com.pet.service.dto.OwnerDTO;

import javax.inject.Singleton;
import java.util.List;

@Singleton
public class OwnerMapperImp  implements OwnerMapper {

    @Override
    public Owner toEntity(OwnerDTO dto) {
        return null;
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
