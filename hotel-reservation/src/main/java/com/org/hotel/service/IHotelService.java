package com.org.hotel.service;

import com.org.hotel.dto.response.HotelResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IHotelService {
    HotelResponse getHotelDetails(Long hotelId) throws Exception;

    List<HotelResponse> getHoteList();
}
