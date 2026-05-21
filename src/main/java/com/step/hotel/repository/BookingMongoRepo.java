package com.step.hotel.repository;

import com.step.hotel.model.Bookings;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingMongoRepo extends MongoRepository<Bookings, String> {
}
