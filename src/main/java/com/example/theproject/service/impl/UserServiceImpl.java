package com.example.theproject.service.impl;

import com.example.theproject.model.entity.Role;
import com.example.theproject.model.entity.User;
import com.example.theproject.model.entity.enums.RoleNameEnums;
import com.example.theproject.model.service.UserServiceModel;
import com.example.theproject.repository.RoleRepository;
import com.example.theproject.repository.UserRepository;
import com.example.theproject.service.UserService;
import org.modelmapper.ModelMapper;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
                           ModelMapper modelMapper, PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void registerUser(UserServiceModel userServiceModel) {

        Role role = new Role();
        role.setRole(RoleNameEnums.USER);

        User user = modelMapper.map(userServiceModel, User.class);
        user.setPassword(passwordEncoder.encode(userServiceModel.getPassword()));
        user.setRoles(Set.of(role));

//        if (userRepository.count() == 0){
//        }

        userRepository.save(user);

        UserDetails userDetails = userDetailsService
                .loadUserByUsername(user.getUsername());

        Authentication authentication =
                new UsernamePasswordAuthenticationToken(
                        userDetails,
                        user.getPassword(),
                        userDetails.getAuthorities()
                );
        SecurityContextHolder.getContext()
                .setAuthentication(authentication);

    }

    @Override
    public UserServiceModel isUsernameExists(String username) {

        return userRepository.findByUsername(username)
                .map(user -> modelMapper.map(user, UserServiceModel.class))
                .orElse(null);
    }

    @Override
    public UserServiceModel isEmailExists(String email) {
        return userRepository.findByEmail(email)
                .map(user -> modelMapper.map(user, UserServiceModel.class))
                .orElse(null);
    }

    @Override
    public void initRole() {

        if (roleRepository.count() != 0) {
            return;
        }

        Arrays.stream(RoleNameEnums.values())
                .forEach(roleName -> {
                    Role role = new Role();
                    role.setRole(roleName);

                    roleRepository.save(role);
                });

//        Role roleAdmin = new Role();
//        roleAdmin.setRole(RoleNameEnums.ADMINISTRATOR);
//
//        Role roleUser = new Role();
//        roleUser.setRole(RoleNameEnums.USER);
//
//        roleRepository.save(roleAdmin);
//        roleRepository.save(roleUser);

    }
}
