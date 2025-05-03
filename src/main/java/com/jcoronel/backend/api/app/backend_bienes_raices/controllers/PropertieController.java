package com.jcoronel.backend.api.app.backend_bienes_raices.controllers;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jcoronel.backend.api.app.backend_bienes_raices.entities.Propertie;
import com.jcoronel.backend.api.app.backend_bienes_raices.entities.Vendor;
import com.jcoronel.backend.api.app.backend_bienes_raices.services.PropertieService;
import com.jcoronel.backend.api.app.backend_bienes_raices.services.VendorService;
import com.jcoronel.backend.api.app.backend_bienes_raices.storage.StorageService;

//import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/properties")
public class PropertieController {

    @Autowired
    private PropertieService service;

    @Autowired
    private VendorService vendorService;

    //@Autowired
    private final StorageService storageService;

    //@Autowired
	public PropertieController(StorageService storageService) {
        this.storageService = storageService;
    }
    
    @GetMapping()
    public List<Propertie> findAll() {
        return service.findAll();
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<?> createPropertyImage(
        @RequestParam("title") String title,
        @RequestParam("price") Double price,
        @RequestParam("image") MultipartFile image,
        @RequestParam("description") String description,
        @RequestParam("rooms") Integer rooms,
        @RequestParam("bathrooms") Integer bathrooms,
        @RequestParam("parking") Integer parking,
        @RequestParam("date") LocalDate date,
        @RequestParam("vendor_id") Long vendor_id
    ){
        Propertie propertieBD = new Propertie();
        Optional<Vendor> verdorOptional = vendorService.findById(vendor_id);

        storageService.store(image);

        if (verdorOptional.isPresent()) {
            propertieBD.setTitle(title);
            propertieBD.setPrice(price);
            propertieBD.setImage(image.getOriginalFilename());
            propertieBD.setDescription(description);
            propertieBD.setRooms(rooms);
            propertieBD.setBathrooms(bathrooms);
            propertieBD.setParking(parking);
            propertieBD.setDate(date);
            propertieBD.setVendor(verdorOptional.get());

            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(propertieBD));
        }

        return ResponseEntity.badRequest().body(Map.of("error", "El vendedor con el id " + vendor_id + " no existe"));
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(e -> {
            errors.put(e.getField(), "El campo " + e.getField() + " " + e.getDefaultMessage());
        });

        return ResponseEntity.badRequest().body(errors);
    }
}
