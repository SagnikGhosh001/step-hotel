package com.step.hotel.service;

import com.step.hotel.model.User;

public interface UserService {
    boolean isUserExist(String username);


    User createUser(String username, String password);
}
