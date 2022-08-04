package com.example.theproject.service.impl.impl;

import com.example.theproject.model.entity.User;
import com.example.theproject.model.entity.enums.RoleNameEnums;
import com.example.theproject.model.service.UserServiceModel;
import com.example.theproject.repository.RoleRepository;
import com.example.theproject.repository.UserRepository;
import com.example.theproject.service.impl.UserService;
import org.modelmapper.ModelMapper;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;

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

        User user = modelMapper.map(userServiceModel, User.class);
        user.setPassword(passwordEncoder.encode(userServiceModel.getPassword()));
        user.setRoles(List.of(roleRepository.findByRole(RoleNameEnums.USER)));

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
    public void initAdmin() {


        User user = new User();
        user.setUsername("dimitar");
        user.setPassword(passwordEncoder.encode("123"));
        user.setFullName("Dimitar Stavrev");
        user.setAge(33);
        user.setEmail("stavrev@abv.com");
        user.setRoles(List.of(roleRepository.findByRole(RoleNameEnums.ADMINISTRATOR)));

        if (userRepository.findByUsername(user.getUsername()).isPresent()){
            return;
        }
        userRepository.save(user);
    }

    @Override
    public void initUser() {

        User user = new User();
        user.setUsername("stavrev");
        user.setPassword(passwordEncoder.encode("123"));
        user.setFullName("Georgi Georgiev");
        user.setAge(27);
        user.setEmail("georgi@gmail.bg");
        user.setRoles(List.of(roleRepository.findByRole(RoleNameEnums.USER)));

        if (userRepository.findByUsername(user.getUsername()).isPresent()){
            return;
        }
        userRepository.save(user);
    }


}
