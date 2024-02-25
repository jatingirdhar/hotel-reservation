package com.org.hotel.service.impl;

import com.org.hotel.dto.response.HotelResponse;
import com.org.hotel.dto.response.RoomResponse;
import com.org.hotel.model.Hotel;
import com.org.hotel.model.Room;
import com.org.hotel.repository.HotelDao;
import com.org.hotel.service.IHotelService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class HotelService implements IHotelService {
    private HotelDao hotelDao;

    @Override
    public HotelResponse getHotelDetails(Long hotelId) throws Exception {
        Optional<Hotel> hotelOpt = hotelDao.findById(hotelId);
        if (!hotelOpt.isPresent()) {
            throw new Exception("Hotel not found.");
        }
        Hotel hotel = hotelOpt.get();
        List<Room> rooms = hotel.getRooms();
        HotelResponse hotelResponse = HotelResponse.builder().name(hotel.getName()).hotelTelephone(hotel.getPhone())
                .hotelId(hotel.getId()).build();
        if (rooms != null && !rooms.isEmpty()) {
            hotelResponse.setRooms(rooms.stream().map(this::populateRoomResponse).collect(Collectors.toList()));
        }
        return hotelResponse;
    }

    @Override
    public List<HotelResponse> getHoteList() {
        List<HotelResponse> hotelResponseList = new ArrayList<>();
        List<Hotel> hotelList = hotelDao.findAll();
        if (hotelList != null && !hotelList.isEmpty()) {
            hotelResponseList.addAll(hotelList.stream().map(this::populateHotelResponse).collect(Collectors.toList()));
        }
        return hotelResponseList;
    }

    private RoomResponse populateRoomResponse(Room room) {
        return RoomResponse.builder().roomId(room.getId()).roomNumber(room.getRoomNumber()).roomType(room.getType())
                .noOfPerson(room.getNoOfPerson()).price(room.getPrice()).build();
    }

    private HotelResponse populateHotelResponse(Hotel hotel) {
        return HotelResponse.builder().name(hotel.getName()).hotelTelephone(hotel.getPhone()).hotelId(hotel.getId())
                .build();
    }
}
