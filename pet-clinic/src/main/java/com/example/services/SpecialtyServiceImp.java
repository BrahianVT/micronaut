package com.example.services;

import com.example.domain.Specialty;
import com.example.repository.SpecialtyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;

@Singleton
public class SpecialtyServiceImp  implements SpecialtyService {
    private final Logger log = LoggerFactory.getLogger(SpecialtyServiceImp.class);

    private final SpecialtyRepository specialtyRepository;
    public SpecialtyServiceImp(SpecialtyRepository specialtyRepository){
        this.specialtyRepository = specialtyRepository;
    }

    @Override
    public Collection<Specialty> findAll() throws Exception {
        log.debug("Request to get all Specialties");
        return specialtyRepository.findAll();
    }

    @Override
    public Optional<Specialty> findOne(Long id) throws Exception {
        log.debug("Request find one specialty " + id);
        return Optional.ofNullable(specialtyRepository.findById(id));
    }

    @Override
    public void delete(Long id) throws Exception {
        log.debug("Delete Specialty" + id);
        specialtyRepository.deleteById(id);
    }

    @Override
    public Specialty save(Specialty specialty) throws Exception {
        log.debug("Save specialty :" + specialty);
        Long id = specialtyRepository.save(specialty);
        return specialtyRepository.findById(id);
    }
}
