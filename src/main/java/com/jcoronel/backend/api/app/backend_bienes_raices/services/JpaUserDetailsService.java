package com.jcoronel.backend.api.app.backend_bienes_raices.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jcoronel.backend.api.app.backend_bienes_raices.entities.User;
import com.jcoronel.backend.api.app.backend_bienes_raices.repositories.UserRepository;


//Clase para hacer login
@Service
public class JpaUserDetailsService implements UserDetailsService{

    @Autowired
    private UserRepository repository;

    //El username viene del login
    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        
        Optional<User> userOptional = repository.findByEmail(email);

        if (!userOptional.isPresent()) {
            throw new UsernameNotFoundException(String.format("Email %s no existe en el sistema!", email));
        }

        User user = userOptional.orElseThrow();

        List<GrantedAuthority> authorities = user.getRoles()
        .stream()
        .map(rol -> new SimpleGrantedAuthority(rol.getName()))
        .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(user.getEmail(), 
        user.getPassword(), 
        user.isEnable(),
        true,
        true,
        true,
        authorities);
    }
}
