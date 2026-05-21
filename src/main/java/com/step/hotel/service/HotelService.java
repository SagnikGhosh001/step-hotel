package com.step.hotel.service;

import com.step.hotel.views.HotelBookingRequestView;
import com.step.hotel.views.HotelBookingResponseView;
import com.step.hotel.views.HotelRequestView;
import com.step.hotel.views.HotelResponseView;

import java.util.List;

public interface HotelService {
    List<HotelResponseView> searchHotels(String city);

    HotelResponseView createHotel(HotelRequestView hotelRequestView);

    HotelBookingResponseView bookHotel(HotelBookingRequestView hotelBookingRequestView, String userId);

    List<HotelBookingResponseView> listBookings(String userId);
}
