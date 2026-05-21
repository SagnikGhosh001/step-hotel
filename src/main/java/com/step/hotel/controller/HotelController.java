package com.step.hotel.controller;

import com.step.hotel.service.HotelService;
import com.step.hotel.views.HotelRequestView;
import com.step.hotel.views.HotelResponseView;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class HotelController {
    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping("/search/hotels")
    public ResponseEntity<List<HotelResponseView>> searchHotels(@RequestParam String city) {
        List<HotelResponseView> hotels = hotelService.searchHotels(city);
        return new ResponseEntity<>(hotels, HttpStatus.OK);
    }

    @PostMapping("/hotel/add")
    public ResponseEntity<HotelResponseView> createHotels(@RequestBody HotelRequestView hotelRequestView) {
        HotelResponseView hotel = hotelService.createHotel(hotelRequestView);
        return new ResponseEntity<>(hotel, HttpStatus.OK);
    }

//    @PostMapping("/bookings")
//    public ResponseEntity<?> post(@RequestBody ? value) {
//
//
//    }

}
