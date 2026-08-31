package com.diegomartinez.system.service;

import com.diegomartinez.system.model.User;
import com.diegomartinez.system.repository.UserRepository;

public class UserService {

    private UserRepository userRepo = new UserRepository();

    public UserStatus createUser(String user, String name, String lastName, String email, String password) {
        try {
            User newUser = new User(name, lastName, email, password, user);
            userRepo.create(newUser);
            return UserStatus.USER_CREATED;
        } catch (Exception e) {
            return UserStatus.ERROR_USER_CREATE;
        }
    }

    public UserStatus login(String user, String password) {
        User userEncontrado = userRepo.findByUserAndPassword(user, password);
        if (userEncontrado != null) {
            return UserStatus.LOGIN_SUCCESS;
        } else {
            return UserStatus.LOGIN_ERROR;
        }
    }

}
