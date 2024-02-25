package com.org.hotel.repository;

import com.org.hotel.enums.ReservationStatusEnum;
import com.org.hotel.model.Customer;
import com.org.hotel.model.Hotel;
import com.org.hotel.model.Reservation;
import com.org.hotel.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReservationDao extends JpaRepository<Reservation, Long> {
    @Modifying
    @Query("update Reservation set status=?2 where id=?1")
    void updateReservationStatus(Long id, ReservationStatusEnum reservationStatusEnum);

    List<Reservation> findByRoomAndStatusAndHotel(Room room, ReservationStatusEnum booked, Hotel hotel);

    List<Reservation> findByCustomer(Customer customer);
}
