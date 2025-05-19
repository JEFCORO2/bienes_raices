package com.jcoronel.backend.api.app.backend_bienes_raices.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

import jakarta.validation.Valid;

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

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id){
        Optional<Propertie> prOptional = service.findById(id);
        
        if (prOptional.isPresent()) {
            return ResponseEntity.ok().body(prOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<?> createProperty(@Valid @ModelAttribute Propertie propertie, 
                                            BindingResult result, @RequestParam("file") 
                                            MultipartFile image){

        if (image.isEmpty() || image == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "Deberias enviar una imagen de la propiedad"));
        } else {
            propertie.setImage(image.getOriginalFilename());
        }

        if (result.hasFieldErrors() ) {
            return validation(result);
        }

        Long vendor_id = propertie.getVendor().getId();
        Optional<Vendor> verdorOptional = vendorService.findById(vendor_id);

        storageService.store(image);

        if (verdorOptional.isPresent()) {
            
            propertie.setVendor(verdorOptional.get());
            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(propertie));
        }

        return ResponseEntity.badRequest().body(Map.of("error", "El vendedor con el id " + vendor_id + " no existe"));
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });

        //para retronar como error, con el estado 400 , que hah sido un error
        return ResponseEntity.badRequest().body(errors);
    }
}
