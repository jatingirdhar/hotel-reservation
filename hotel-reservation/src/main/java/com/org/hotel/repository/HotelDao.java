package com.org.hotel.repository;

import com.org.hotel.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelDao extends JpaRepository<Hotel, Long> {
}
