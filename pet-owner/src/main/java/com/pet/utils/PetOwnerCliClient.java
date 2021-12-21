package com.pet.utils;

import com.pet.domain.Owner;
import com.pet.domain.Pet;
import com.pet.domain.Visit;
import com.pet.service.OwnerService;
import com.pet.service.PetService;
import com.pet.service.PetTypeService;
import com.pet.service.VisitService;
import io.micronaut.core.util.CollectionUtils;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Singleton
public class PetOwnerCliClient {
    private final Logger log = LoggerFactory.getLogger(PetOwnerCliClient.class);

    private final OwnerService ownerService;
    private final PetService petService;
    private final VisitService visitService;
    private final PetTypeService petTypeService;


    public PetOwnerCliClient(OwnerService ownerService, PetService petService, VisitService visitService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.petService = petService;
        this.visitService = visitService;
        this.petTypeService = petTypeService;
    }

    public Owner initOwner(){
        Owner owner = new Owner();
        owner.setFirstName("Foo");
        owner.setCity("Bar");
        owner.setAddress("404 Adelaide St W");
        owner.setTelephone("647000999");

        Pet pet = new Pet();
        pet.setType(petTypeService.findAll(Pageable.unpaged()).getContent().get(1));
        pet.setName("Baz");
        pet.setBirthDate(LocalDate.of(2010, 12,12));
        pet.setOwner(owner);

        Visit visit = new Visit();
        visit.setVisitDate(LocalDate.now());
        visit.setDescription("Breathing issues");
        visit.setPet(pet);

        return owner;
    }

    protected  void performFindAll(){
        log.info("-------------------------------------------");
        log.info("Request to performFindAll");
        log.info("-------------------------------------------");
        Page<Owner> pOwners = ownerService.findAll(Pageable.unpaged());

        if(CollectionUtils.isNotEmpty(pOwners.getContent())){
            List<Owner> owners = pOwners.getContent();
            owners.forEach( owner -> {
                log.info("Owner: {}, {}", owner.getFirstName(), owner.getLastName());
            });
        }
    }

    protected void performFindOne(Long id){
        log.info("-------------------------------------------");
        log.info("Request to PerformFindOne for id :", id);
        log.info("-------------------------------------------");

        Optional<Owner> oOwner = ownerService.findOne(id);
        oOwner.ifPresent(owner -> log.info("Owner: {} {}", owner.getFirstName(), owner.getLastName()));
    }

    protected Owner performSave(){
        Owner owner = initOwner();
        log.info("-------------------------------------------");
        log.info("Request to performSave for owner {}" , owner);
        log.info("-------------------------------------------");

        return ownerService.save(owner);
    }

    protected  void performDelete(Owner owner){
        log.info("-------------------------------------------");
        log.info("Request to performDelete owner" , owner);
        log.info("-------------------------------------------");

        Set<Pet> pets = owner.getPets();

        if(!CollectionUtils.isNotEmpty(pets)){
            for(Pet pet: pets){
                Set<Visit> visits = pet.getVisits();
                if(visits.size() == 0){
                    for(Visit v : visits)
                        visitService.delete(v.getId());
                }
                petService.delete(pet.getId());
            }
        }
    }

}
