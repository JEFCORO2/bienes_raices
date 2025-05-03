package com.jcoronel.backend.api.app.backend_bienes_raices.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.jcoronel.backend.api.app.backend_bienes_raices.entities.User;

public interface UserRepository extends CrudRepository<User, Long>{
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
}
