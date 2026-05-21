package com.step.hotel.model;

import com.step.hotel.views.HotelResponseView;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "hotels")
public class Hotel {
    @Id
    private String id;

    private final String hotelName;
    private final String city;
    private final int totalRooms;
    private final int availableRooms;

    public Hotel(String hotelName, String city, int totalRooms) {
        this.hotelName = hotelName;
        this.city = city;
        this.totalRooms = totalRooms;
        this.availableRooms = totalRooms;
    }

    public HotelResponseView toResponse() {
        return new HotelResponseView(id, hotelName, city, availableRooms);
    }
}
