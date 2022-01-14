package com.example.services;

import com.example.domain.Specialty;
import com.example.domain.Vet;
import com.example.repository.SpecialtyRepository;
import com.example.repository.VetRepository;
import io.micronaut.core.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;

@Singleton
public class VetServiceImp implements VetService {
    private final Logger log = LoggerFactory.getLogger(VetServiceImp.class);

    private final VetRepository vetRepository;
    private final SpecialtyRepository specialtyRepository;

    public VetServiceImp(VetRepository vetRepository, SpecialtyRepository specialtyRepository){
        this.vetRepository = vetRepository;
        this.specialtyRepository = specialtyRepository;
    }
    @Override
    public Vet save(Vet vet) throws Exception {
        log.debug("Save Vet" + vet);
        Vet saveVet = null;
        Long vetId = vetRepository.save(vet);
        saveSpecialties(vetId, vet.getSpecialties());
        saveVet = vetRepository.findById(vetId);
        return saveVet;
    }

    @Override
    public Collection<Vet> findAll() throws Exception {
        log.debug("Get all vets");
        Collection<Vet> vets = vetRepository.findAll();
        vets.forEach( vet -> {
            try{
                Set<Specialty> specialties = specialtyRepository.findByVetId(vet.getId());
                if(CollectionUtils.isNotEmpty(specialties)){
                    vet.setSpecialties(specialties);
                }
            }catch(Exception e){
                log.error(" Exception : " + e.toString());
            }
        });
        return vets;
    }

    @Override
    public Optional<Vet> findOne(Long id) throws Exception {
        log.debug("find One Vet: " + id);
        Vet vet = vetRepository.findById(id);
        if(vet != null){
            try {
              Set<Specialty> specialties = specialtyRepository.findByVetId(vet.getId());
              if(CollectionUtils.isNotEmpty(specialties)) vet.setSpecialties(specialties);
            } catch(Exception e){
                log.error("Exception: " + e.toString());
            }
        }
        return Optional.ofNullable(vet);
    }

    @Override
    public void delete(Long id) throws Exception {
        log.debug("Delete Vet " + id);

        Optional<Vet> vet = findOne(id);
        if(vet.isPresent()){
            if(CollectionUtils.isNotEmpty(vet.get().getSpecialties()))
                vetRepository.deleteVetSpecialtyById(vet.get().getId());
            vetRepository.deleteById(id);
        }
    }

    private void saveSpecialties(Long vetId, Set<Specialty> specialties) {
        log.debug("Request to save Specialties in the Vet wwith id: "  + vetId);

        if(CollectionUtils.isNotEmpty(specialties)){
           specialties.forEach( specialty -> {
               try{
                   Long specialtyId;
                   Specialty dbSpecialty = specialtyRepository.findByName(specialty.getName().toUpperCase().trim());
                   specialtyId = dbSpecialty != null?dbSpecialty.getId():specialtyRepository.save(specialty);

                   vetRepository.saveVetSpecialty(vetId, specialtyId);
               }catch(Exception e){
                   log.error("Exception :" + e.toString());
               }
           });
        }
    }

    @Override
    public void updateVetAverageRating(Long vetId, Double rating) throws Exception {
        log.debug("Request to update vet rating, id: {}, rating: {}", vetId, rating);
        Optional<Vet> oVet = findOne(vetId);

        if(oVet.isPresent()) {
            Double averageRating = oVet.get().getAverageRating() != null ? oVet.get().getAverageRating() : 0D;
            Long ratingCount = oVet.get().getRatingCount() != null ? oVet.get().getRatingCount() : 0L;
            Double newAvgRating = ((averageRating * ratingCount) + rating) / (ratingCount + 1);
            Long newRatingCount = ratingCount + 1;

            vetRepository.updateVetAverageRating(vetId, newAvgRating, newRatingCount);
        }

    }
}
