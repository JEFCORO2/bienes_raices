package com.jcoronel.backend.api.app.backend_bienes_raices.repositories;

import org.springframework.data.repository.CrudRepository;

import com.jcoronel.backend.api.app.backend_bienes_raices.entities.Propertie;

public interface PropertieRepository extends CrudRepository<Propertie, Long>{
    
}
