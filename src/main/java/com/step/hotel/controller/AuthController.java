package com.step.hotel.controller;

import com.step.hotel.model.User;
import com.step.hotel.service.UserService;
import com.step.hotel.views.UserRequestView;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users")
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> signup(@RequestBody UserRequestView userRequestView) {
        User user = userService.createUser(userRequestView.username(), userRequestView.password());
        return ResponseEntity.ok("user created successfully");
    }

//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody UserRequetView userRequetView) {
//
//    }
}
