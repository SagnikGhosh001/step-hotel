package com.step.hotel.mongoServiceImple;

import com.step.hotel.model.Hotel;
import com.step.hotel.repository.HotelMongoRepo;
import com.step.hotel.service.HotelService;
import com.step.hotel.views.HotelRequestView;
import com.step.hotel.views.HotelResponseView;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelServiceImpl implements HotelService {

    private final HotelMongoRepo hotelMongoRepo;

    public HotelServiceImpl(HotelMongoRepo hotelMongoRepo) {
        this.hotelMongoRepo = hotelMongoRepo;
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
}
