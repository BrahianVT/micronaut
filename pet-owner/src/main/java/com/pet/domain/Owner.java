package com.pet.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="owners", schema="petowner")
public class Owner implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="address")
    private String address;

    @Column(name="city")
    private String city;

    @Column(name="telephone")
    private String telephone;

    @OneToMany(mappedBy = "owner", orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<Pet> pets = new HashSet<>();


    public Owner firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }
    public Owner lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
    public Owner address(String address) {
        this.address = address;
        return this;
    }
    public Owner city(String city) {
        this.city = city;
        return this;
    }

    public Owner telephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    public Owner pets(Set<Pet> pets) {
        this.pets = pets;
        return this;
    }

    public Owner addPet(Pet pet) {
        this.pets.add(pet);
        pet.setOwner(this);
        return this;
    }

    public Owner removePet(Pet pet) {
        this.pets.remove(pet);
        pet.setOwner(null);
        return this;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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

    public Set<Pet> getPets() {
        return pets;
    }

    public void setPets(Set<Pet> pets) {
        this.pets = pets;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }
}
