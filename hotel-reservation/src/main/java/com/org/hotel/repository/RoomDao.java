package com.org.hotel.repository;

import com.org.hotel.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomDao extends JpaRepository<Room, Long> {
    Optional<Room> findByIdAndHotelId(Long roomId, Long hotelId);
}
