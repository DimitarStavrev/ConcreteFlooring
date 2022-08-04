package com.example.theproject.service.impl;

import com.example.theproject.model.service.UserServiceModel;

public interface UserService {

    void registerUser(UserServiceModel userServiceModel);

    UserServiceModel isUsernameExists(String username);

    UserServiceModel isEmailExists(String email);

    void initAdmin();

    void initUser();
}
