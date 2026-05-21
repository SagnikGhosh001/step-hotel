package com.step.hotel.repository;

import com.step.hotel.model.Hotel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelMongoRepo extends MongoRepository<Hotel, String> {
    List<Hotel> findByCity(String city);
}
