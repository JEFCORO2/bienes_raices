package com.jcoronel.backend.api.app.backend_bienes_raices.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jcoronel.backend.api.app.backend_bienes_raices.entities.Vendor;
import com.jcoronel.backend.api.app.backend_bienes_raices.repositories.VendorRepository;

@Service
public class VendorServiceImpl implements VendorService{

    @Autowired
    private VendorRepository vendorRepository;

    @Override
    public List<Vendor> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public Vendor save(Vendor v) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public Optional<Vendor> findById(Long id) {
        Optional<Vendor> vendorOptional = vendorRepository.findById(id);

        if (vendorOptional.isPresent()) {
            Vendor vendor = vendorRepository.findById(id).get();
            return Optional.of(vendor);
        }

        return vendorOptional;
    }

}
