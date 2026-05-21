package com.step.hotel.model;

import com.step.hotel.views.HotelBookingResponseView;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Bookings {
    @Id
    private String id;

    private final User user;
    private final Hotel hotel;
    private final int rooms;

    public Bookings(User user, Hotel hotel, int rooms) {
        this.user = user;
        this.hotel = hotel;
        this.rooms = rooms;
    }

    public HotelBookingResponseView toResponse() {
        return new HotelBookingResponseView(id, hotel.toResponse(), rooms);
    }

    public String generateReceipt() {
        return String.format("you have booked %d rooms", rooms);
    }
}
