package com.step.hotel.mongoServiceImple;

import com.step.hotel.exceptions.BadRequestException;
import com.step.hotel.model.User;
import com.step.hotel.repository.UserRepo;
import com.step.hotel.service.JwtService;
import com.step.hotel.service.UserService;
import com.step.hotel.views.UserRequestView;
import com.step.hotel.views.UserResponseView;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final JwtService jwtService;

    public UserServiceImpl(UserRepo userRepo, JwtService jwtService) {
        this.userRepo = userRepo;
        this.jwtService = jwtService;
    }

    @Override
    public boolean isUserExist(String username) {
        User user = userRepo.findByUsername(username);
        return user != null;
    }

    @Override
    public UserResponseView createUser(UserRequestView userRequestView) {
        boolean isUserExist = isUserExist(userRequestView.username());
        if (isUserExist) {
            throw new BadRequestException("user already exists");
        }

        User newUser = new User(userRequestView.username(), userRequestView.password());
        return userRepo.save(newUser).toResponse();
    }

    @Override
    public String loginUser(UserRequestView userRequestView) {
        User user = userRepo.findByUsername(userRequestView.username());

        if (user == null) {
            throw new BadRequestException(String.format("user not found : %s", userRequestView.username()));
        }

        return jwtService.generateToken(user.toResponse().id());
    }


}
