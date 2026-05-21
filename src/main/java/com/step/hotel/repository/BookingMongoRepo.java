package com.step.hotel.repository;

import com.step.hotel.model.Bookings;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingMongoRepo extends MongoRepository<Bookings, String> {
    List<Bookings> findByUser_Id(String userId);
}
