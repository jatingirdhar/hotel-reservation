package com.org.hotel.controller;

import com.org.hotel.dto.response.HotelResponse;
import com.org.hotel.service.IHotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/hotel")
public class HotelController {
    @Autowired
    IHotelService hotelService;

    @PutMapping("/list")
    public ResponseEntity<List<HotelResponse>> getHotelList() {
        return new ResponseEntity<>(hotelService.getHoteList(), HttpStatus.OK);
    }

    @GetMapping("detail/id/{hotelId}")
    public ResponseEntity<HotelResponse> getHotelDetails(@PathVariable("hotelId") Long hotelId) throws Exception {
        return new ResponseEntity<>(hotelService.getHotelDetails(hotelId), HttpStatus.OK);
    }
}
