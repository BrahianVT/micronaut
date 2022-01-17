package com.pet.service.dto;

import io.micronaut.core.annotation.Introspected;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Introspected
public class OwnerDTO implements Serializable {
    private Long id;
    private String firstNam, lastName, address, city, telephone;

    private Set<PetDTO> pets = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstNam() {
        return firstNam;
    }

    public void setFirstNam(String firstNam) {
        this.firstNam = firstNam;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Set<PetDTO> getPets() {
        return pets;
    }

    public void setPets(Set<PetDTO> pets) {
        this.pets = pets;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", firstNam='" + firstNam + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }
}
