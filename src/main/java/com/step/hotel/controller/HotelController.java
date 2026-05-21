package com.step.hotel.controller;

import com.step.hotel.service.HotelService;
import com.step.hotel.views.HotelResponseView;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class HotelController {
    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping("/search/hotels/{city}")
    public ResponseEntity<List<HotelResponseView>> searchHotels(@PathVariable String city) {
        List<HotelResponseView> hotels = hotelService.searchHotels(city);
        return new ResponseEntity<>(hotels, HttpStatus.OK);
    }
}
