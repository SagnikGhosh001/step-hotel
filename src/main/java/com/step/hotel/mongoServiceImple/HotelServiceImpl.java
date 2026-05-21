package com.step.hotel.mongoServiceImple;

import com.step.hotel.exceptions.BadRequestException;
import com.step.hotel.model.Bookings;
import com.step.hotel.model.Hotel;
import com.step.hotel.model.User;
import com.step.hotel.repository.BookingMongoRepo;
import com.step.hotel.repository.HotelMongoRepo;
import com.step.hotel.repository.UserMongoRepo;
import com.step.hotel.service.HotelService;
import com.step.hotel.views.HotelBookingRequestView;
import com.step.hotel.views.HotelBookingResponseView;
import com.step.hotel.views.HotelRequestView;
import com.step.hotel.views.HotelResponseView;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelServiceImpl implements HotelService {

    private final HotelMongoRepo hotelMongoRepo;
    private final UserMongoRepo userMongoRepo;
    private final BookingMongoRepo bookingMongoRepo;

    public HotelServiceImpl(HotelMongoRepo hotelMongoRepo, UserMongoRepo userMongoRepo, BookingMongoRepo bookingMongoRepo) {
        this.hotelMongoRepo = hotelMongoRepo;
        this.userMongoRepo = userMongoRepo;
        this.bookingMongoRepo = bookingMongoRepo;
    }

    @Override
    public List<HotelResponseView> searchHotels(String city) {
        return hotelMongoRepo.findByCity(city).stream().map(Hotel::toResponse).toList();
    }

    @Override
    public HotelResponseView createHotel(HotelRequestView hotelRequestView) {
        Hotel hotel = new Hotel(hotelRequestView.name(), hotelRequestView.city(), hotelRequestView.totalRoom());
        Hotel save = hotelMongoRepo.save(hotel);
        return save.toResponse();
    }

    @Override
    public HotelBookingResponseView bookHotel(HotelBookingRequestView hotelBookingRequestView, String userId) {
        Hotel hotel = hotelMongoRepo.findById(hotelBookingRequestView.hotel_id()).orElseThrow(() -> new BadRequestException(String.format("Hotel not found with id %s", hotelBookingRequestView.hotel_id())));

        User user = userMongoRepo.findById(userId).orElseThrow(() -> new BadRequestException(String.format("User not found with id %s", userId)));

        if (!hotel.isRoomAvailable(hotelBookingRequestView.rooms())) {
            throw new BadRequestException("Rooms not available");
        }

        Bookings bookings = hotel.bookRoom(user, hotelBookingRequestView.rooms());
        Bookings savedBookings = bookingMongoRepo.save(bookings);


        hotelMongoRepo.save(hotel);

        return savedBookings.toResponse();
    }

    @Override
    public List<HotelBookingResponseView> listBookings(String userId) {
        User user = userMongoRepo.findById(userId).orElseThrow(() -> new BadRequestException(String.format("User not found with id %s", userId)));

        List<HotelBookingResponseView> bookingsList = bookingMongoRepo.findByUser_Id(userId).stream().map(Bookings::toResponse).toList();

        return bookingsList;
    }

    @Override
    public String getReceipt(String userId, String bookingId) {
        User user = userMongoRepo.findById(userId).orElseThrow(() -> new BadRequestException(String.format("User not found with id %s", userId)));

        Bookings booking = bookingMongoRepo.findById(bookingId).orElseThrow(() -> new BadRequestException(String.format("booking not found with id %s", bookingId)));

        return booking.generateReceipt();
    }


}
