package com.step.hotel.views;

public record HotelBookingResponseView(String booking_id, HotelResponseView hotel, int roomsBooked) {
}
