package com.pet.service.dto;

import io.micronaut.core.annotation.Introspected;

import java.io.Serializable;

@Introspected
public class PetTypeDTO  implements Serializable {

    private Long id;
    private String name;

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

    @Override
    public String toString() {
        return "PetTypeDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
