package com.jcoronel.backend.api.app.backend_bienes_raices.services;

import java.util.List;

import com.jcoronel.backend.api.app.backend_bienes_raices.entities.User;


public interface UserService {
    List<User> findAll();
    User save(User user);
    boolean existsByEmail(String email);
}
