package com.example.theproject.service;

import com.example.theproject.model.service.UserServiceModel;

public interface UserService {

    void registerUser(UserServiceModel userServiceModel);

    UserServiceModel isUsernameExists(String username);

    UserServiceModel isEmailExists(String email);

    void initRole();
}
