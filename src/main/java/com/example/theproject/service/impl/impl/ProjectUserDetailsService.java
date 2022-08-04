package com.example.theproject.service.impl.impl;

import com.example.theproject.model.entity.Role;
import com.example.theproject.model.entity.User;
import com.example.theproject.model.user.ProjetUserDetails;
import com.example.theproject.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;



public class ProjectUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public ProjectUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return userRepository
                .findByUsername(username)
                .map(this::map)
                .orElseThrow(() -> new UsernameNotFoundException
                        ("Потребителското име" + username + " не е намерено."));


    }

    private UserDetails map(User user) {
        return new ProjetUserDetails(
                user.getPassword(),
                user.getUsername(),
                user.getFullName(),
                user.getRoles()
                        .stream()
                        .map(this::map)
                        .toList()
        );
//        return org.springframework.security.core.userdetails.User.builder()
//                .username(user.getUsername())
//                .password(user.getPassword())
//                .authorities(user
//                        .getRoles()
//                        .stream()
//                        .map(this::map)
//                        .collect(Collectors.toList()))
//                .build();
    }

    private GrantedAuthority map(Role role) {
        return new SimpleGrantedAuthority("ROLE_" + role.getRole().name());
    }
}
