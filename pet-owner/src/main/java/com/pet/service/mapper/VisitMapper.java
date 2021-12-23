package com.pet.service.mapper;

import com.pet.domain.Visit;
import com.pet.service.dto.VisitDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "jsr330", uses = { })
public interface VisitMapper extends EntityMapper<VisitDTO, Visit> {
    @Mapping(source = "pet.id" , target = "petId")
    VisitDTO toDto(Visit visit);

    @Mapping(source = "petId", target = "pet.id")
    Visit toEntity(VisitDTO visitDTO);

    default Visit fromId(Long id){
        if(id == null) return null;

        Visit visit = new Visit();
        visit.setId(id);
        return visit;
    }
}
