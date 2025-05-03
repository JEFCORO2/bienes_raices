package com.jcoronel.backend.api.app.backend_bienes_raices.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jcoronel.backend.api.app.backend_bienes_raices.entities.User;
import com.jcoronel.backend.api.app.backend_bienes_raices.services.UserService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin(originPatterns = "*", origins = "http://localhost:4200") //anotacion para la comunicacion con el cliente que esta en otro dominio
@RestController
@RequestMapping("/api/users") //Le damos una ruta base
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    public List<User> list(){
        return service.findAll();
    }

    //Reglas de validacion con anotaciones
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    public ResponseEntity<?> create(@Valid @RequestBody User user, BindingResult result) {
        
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(user));
    }

    //Solo esta ruta sera publica, para todos los usuarios, mira la configuracion del SprignSecurity
    //Como es ruta publica asi el usuario ingrese un json con el admin no lo va toamr porque l ocambia a false
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody User user, BindingResult result) {
        user.setAdmin(false);
        return create(user, result);
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
