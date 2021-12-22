package com.example.utils;


import com.example.domain.Specialty;
import com.example.domain.Vet;
import com.example.services.SpecialtyService;
import com.example.services.VetService;
import io.micronaut.core.util.CollectionUtils;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@Singleton
public class PetClinicCliClient {
    private final Logger log = LoggerFactory.getLogger(PetClinicCliClient.class);

    private final VetService vetService;
    private final SpecialtyService specialtyService;


    public PetClinicCliClient(VetService vetService, SpecialtyService specialtyService) {
        this.vetService = vetService;
        this.specialtyService = specialtyService;
    }

    public void performDatabaseOperations(){
        performFindAll();
        Vet vet = performSave();
        performFindOne(vet.getId());
        performDelete(vet);
        performFindAll();
    }

    private void performDelete(Vet vet) {
        log.info("-----------------------");
        log.info("Request to delete " + vet);
        log.info("-----------------------");

        try {
            vetService.delete(vet.getId());
        } catch(Exception e){
            log.error("Exception : " + e.toString());
        }
    }

    private void performFindOne(Long id) {
        log.info("-----------------------");
        log.info("Request to performFindOne "+ id);
        log.info("-----------------------");

        Optional<Vet> vet;
        try{
            vet = vetService.findOne(id);
            vet.ifPresent(owner -> log.info("Vet: " + owner.getFirstName() + " , " + owner.getLastName()));
        }catch (Exception e){
            log.error(e.toString());
        }
    }

    private Vet performSave() {
        Vet vet = initVet();
        log.info("-----------------------");
        log.info("Request to performSave for vet: " + vet);
        log.info("-----------------------");

        Vet savedVet = null;
        try{
            savedVet = vetService.save(vet);
        }
        catch(Exception e){
            log.error("Exception :" + e.toString());
        }
        return savedVet;
    }

    private Vet initVet() {
        Vet vet = new Vet();
        vet.setFirstName("Bob");
        vet.setLastName("Build");
        Specialty specialty = new Specialty();
        specialty.setName("Baz");
        vet.getSpecialties().add(specialty);
        return vet;
    }

    private void performFindAll() {
        log.info("-----------------------");
        log.info("Request to performFindAll");
        log.info("-----------------------");

        List<Vet> vets;
        try{
            vets = (List<Vet>) vetService.findAll();
            if(CollectionUtils.isNotEmpty(vets)){
                vets.forEach(vet -> {
                    log.info("Ver : " + vet.getFirstName() + ",  " + vet.getLastName());
                });
            }
        }catch(Exception e){
            log.info("Exception: " + e.toString());
        }
    }
}
