package com.step.hotel.model;

import com.step.hotel.views.HotelResponseView;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "hotels")
public class Hotel {
    @Id
    private String id;

    private final String hotelName;
    @Indexed
    private final String city;
    private final int totalRooms;
    private int availableRooms;

    public Hotel(String hotelName, String city, int totalRooms) {
        this.hotelName = hotelName;
        this.city = city;
        this.totalRooms = totalRooms;
        this.availableRooms = totalRooms;
    }

    public HotelResponseView toResponse() {
        return new HotelResponseView(id, hotelName, city, availableRooms);
    }

    public boolean isRoomAvailable(int rooms) {
        return availableRooms >= rooms;
    }

    public Bookings bookRoom(User user, int rooms) {
        availableRooms -= rooms;
        return new Bookings(user, this, rooms);
    }
}
