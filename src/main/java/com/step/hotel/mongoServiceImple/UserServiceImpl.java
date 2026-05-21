package com.step.hotel.mongoServiceImple;

import com.step.hotel.exceptions.BadRequestException;
import com.step.hotel.model.User;
import com.step.hotel.repository.UserRepo;
import com.step.hotel.service.UserService;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public boolean isUserExist(String username) {
        User user = userRepo.findByUsername(username);
        return user != null;
    }

    @Override
    public User createUser(String username, String password) {
        boolean isUserExist = isUserExist(username);
        if (isUserExist) {
            throw new BadRequestException("user already exists");
        }

        User newUser = new User(username, password);
        return userRepo.save(newUser);
    }


}
