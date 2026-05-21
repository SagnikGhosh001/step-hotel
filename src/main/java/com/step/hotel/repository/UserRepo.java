package com.step.hotel.repository;

import com.step.hotel.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<User, String> {
    User findByUsername(String name);
}
