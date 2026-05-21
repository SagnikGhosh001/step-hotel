package com.step.hotel.service;

import com.step.hotel.views.HotelResponseView;

import java.util.List;

public interface HotelService {
    List<HotelResponseView> searchHotels(String city);
}
