package com.jcoronel.backend.api.app.backend_bienes_raices.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.jcoronel.backend.api.app.backend_bienes_raices.entities.Role;

public interface RoleRepository extends CrudRepository<Role, Long>{
    @Query()
    Optional<Role> findByName(String name); 
}
