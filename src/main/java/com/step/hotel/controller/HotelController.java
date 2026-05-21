package com.step.hotel.controller;

import com.step.hotel.service.HotelService;
import com.step.hotel.views.HotelBookingRequestView;
import com.step.hotel.views.HotelBookingResponseView;
import com.step.hotel.views.HotelRequestView;
import com.step.hotel.views.HotelResponseView;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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

    @PostMapping("/bookings")
    public ResponseEntity<HotelBookingResponseView> bookHotel(@RequestBody HotelBookingRequestView hotelBookingRequestView, Authentication authentication) {
        String userId = authentication.getName();
        HotelBookingResponseView hotelBookingResponseView = hotelService.bookHotel(hotelBookingRequestView, userId);
        return new ResponseEntity<>(hotelBookingResponseView, HttpStatus.OK);
    }

    @GetMapping("/bookings")
    public ResponseEntity<List<HotelBookingResponseView>> listBookings(Authentication authentication) {
        String userId = authentication.getName();
        List<HotelBookingResponseView> bookings = hotelService.listBookings(userId);

        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    @GetMapping("/bookings/{bookingId}/receipt")
    public ResponseEntity<String> getReceipt(@PathVariable String bookingId, Authentication authentication) {
        String userId = authentication.getName();
        String receipt = hotelService.getReceipt(userId, bookingId);
        return new ResponseEntity<>(receipt, HttpStatus.OK);
    }

}
