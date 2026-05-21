package com.step.hotel.service;

import com.step.hotel.views.UserRequestView;
import com.step.hotel.views.UserResponseView;

public interface UserService {
    boolean isUserExist(String username);


    UserResponseView createUser(UserRequestView userRequestView);

    String loginUser(UserRequestView userRequestView);
}
