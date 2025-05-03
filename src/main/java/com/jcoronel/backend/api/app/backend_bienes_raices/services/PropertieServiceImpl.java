package com.jcoronel.backend.api.app.backend_bienes_raices.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jcoronel.backend.api.app.backend_bienes_raices.entities.Propertie;
import com.jcoronel.backend.api.app.backend_bienes_raices.repositories.PropertieRepository;

@Service
public class PropertieServiceImpl implements PropertieService{

    @Autowired
    private PropertieRepository propertieRepository;

    @Override
    public List<Propertie> findAll() {
        return (List<Propertie>) propertieRepository.findAll();
    }

    
    @Override
    public Propertie save(Propertie p) {
        p.setDate(LocalDate.now());
        return propertieRepository.save(p);
    }

    @Override
    public Optional<Propertie> findById(Long id) {
        Optional<Propertie> proOptional = propertieRepository.findById(id);

        if (proOptional.isPresent()) {
            Propertie propertie = propertieRepository.findById(id).get();
            return Optional.of(propertie);
        }

        return proOptional;
    }

}
