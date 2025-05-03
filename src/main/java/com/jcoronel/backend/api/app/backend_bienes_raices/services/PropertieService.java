package com.jcoronel.backend.api.app.backend_bienes_raices.services;

import java.util.List;
import java.util.Optional;

import com.jcoronel.backend.api.app.backend_bienes_raices.entities.Propertie;

public interface PropertieService {
    List<Propertie> findAll();
    Propertie save(Propertie p);
    Optional<Propertie> findById(Long id);
}
