package com.gateway.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.core.annotation.Introspected;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Introspected
public class PetDTO  implements Serializable {
    private Long id;
    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    private Set<VisitDTO> visits = new HashSet<>();
    private PetTypeDTO type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Set<VisitDTO> getVisits() {
        return visits;
    }

    public void setVisits(Set<VisitDTO> visits) {
        this.visits = visits;
    }

    public PetTypeDTO getType() {
        return type;
    }

    public void setType(PetTypeDTO type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "PetDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
