package com.org.hotel.service;

import com.org.hotel.dto.request.ReservationRequest;
import com.org.hotel.dto.response.ReservationResponse;
import com.org.hotel.enums.ReservationStatusEnum;
import com.org.hotel.model.Hotel;
import com.org.hotel.model.Reservation;
import com.org.hotel.model.Room;
import com.org.hotel.model.User;
import com.org.hotel.repository.HotelDao;
import com.org.hotel.repository.ReservationDao;
import com.org.hotel.repository.RoomDao;
import com.org.hotel.repository.UserDao;
import com.org.hotel.service.impl.ReservationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReservationServiceTest {
    @Mock
    private ReservationDao reservationDao;
    @Mock
    private HotelDao hotelDao;
    @Mock
    private RoomDao roomDao;
    @Mock
    private UserDao userDao;
    @InjectMocks
    private ReservationService reservationService;

    @Test
    void testCreateReservation() throws Exception {
        // Mocking data
        ReservationRequest reservationRequest = new ReservationRequest();
        reservationRequest.setHotelId(1L);
        reservationRequest.setRoomId(1L);
        reservationRequest.setCheckinDate(LocalDate.now());
        reservationRequest.setCheckoutDate(LocalDate.now().plusDays(1));
        reservationRequest.setNoOfPerson(2);
        User user = new User();
        user.setId(1L);
        Hotel hotel = new Hotel();
        hotel.setId(1L);
        Room room = new Room();
        room.setId(1L);
        room.setHotel(hotel);
        when(userDao.findById(anyLong())).thenReturn(Optional.of(user));
        when(hotelDao.findById(anyLong())).thenReturn(Optional.of(hotel));
        when(roomDao.findByIdAndHotelId(anyLong(), anyLong())).thenReturn(Optional.of(room));
        when(reservationDao.findByRoomAndStatusAndHotel(any(), eq(ReservationStatusEnum.BOOKED), any())).thenReturn(
                Collections.emptyList());
        ReservationResponse reservationResponse = reservationService.createReservation(reservationRequest,
                user.getId());
    }

    @Test
    void testCreateReservation_InvalidHotel() {
        // Mocking data
        ReservationRequest reservationRequest = new ReservationRequest();
        reservationRequest.setHotelId(1L);
        User user = new User();
        user.setId(1L);
        when(userDao.findById(anyLong())).thenReturn(Optional.of(user));
        when(hotelDao.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(Exception.class, () -> reservationService.createReservation(reservationRequest, user.getId()));
    }

    @Test
    void testCreateReservation_InvalidRoom() {
        // Mocking data
        ReservationRequest reservationRequest = new ReservationRequest();
        reservationRequest.setHotelId(1L);
        reservationRequest.setRoomId(1L);
        User user = new User();
        user.setId(1L);
        Hotel hotel = new Hotel();
        hotel.setId(1L);
        when(userDao.findById(anyLong())).thenReturn(Optional.of(user));
        when(hotelDao.findById(anyLong())).thenReturn(Optional.of(hotel));
        when(roomDao.findByIdAndHotelId(anyLong(), anyLong())).thenReturn(Optional.empty());
        assertThrows(Exception.class, () -> reservationService.createReservation(reservationRequest, user.getId()));
    }

    @Test
    void testCancelReservation() throws Exception {
        Long reservationId = 1L;
        Reservation reservation = new Reservation();
        reservation.setId(reservationId);
        when(reservationDao.findById(anyLong())).thenReturn(Optional.of(reservation));
        reservationService.cancelReservation(reservationId);
    }

    @Test
    void testCancelReservation_InvalidReservation() {
        Long reservationId = 1L;
        when(reservationDao.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(Exception.class, () -> reservationService.cancelReservation(reservationId));
    }
}
