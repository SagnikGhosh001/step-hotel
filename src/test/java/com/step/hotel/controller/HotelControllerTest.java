package com.step.hotel.controller;

import com.step.hotel.service.HotelService;
import com.step.hotel.views.HotelRequestView;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.mongodb.test.autoconfigure.AutoConfigureDataMongo;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.client.RestTestClient;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@AutoConfigureRestTestClient
@AutoConfigureDataMongo
class HotelControllerTest {
    @Autowired
    private RestTestClient client;

    @Autowired
    private HotelService hotelService;

    @Test
    void shouldHandleTheSearchRequest() {
        hotelService.createHotel(new HotelRequestView("hotel 1", "city1", 10));
        hotelService.createHotel(new HotelRequestView("hotel 2", "city1", 10));
        hotelService.createHotel(new HotelRequestView("hotel 3", "city2", 10));

        List responseBody = client.get().uri("/api/search/hotels?city=city1")
                .exchange()
                .expectStatus().isOk()
                .expectBody(List.class).returnResult().getResponseBody();

        assertEquals(2, responseBody.size());
    }
}