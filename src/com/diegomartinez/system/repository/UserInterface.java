package com.diegomartinez.system.repository;

import com.diegomartinez.system.model.User;

public interface UserInterface {

    void create(User user);

    User findByUserAndPassword(String user, String password);

}
