package com.jcoronel.backend.api.app.backend_bienes_raices.services;

import java.util.List;
import java.util.Optional;

import com.jcoronel.backend.api.app.backend_bienes_raices.entities.Vendor;

public interface VendorService{
    List<Vendor> findAll();
    Vendor save(Vendor v);
    Optional<Vendor> findById(Long id);
}
