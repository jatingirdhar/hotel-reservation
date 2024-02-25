package com.org.hotel.service.impl;

import com.org.hotel.dto.request.ReservationRequest;
import com.org.hotel.dto.response.HotelResponse;
import com.org.hotel.dto.response.ReservationResponse;
import com.org.hotel.dto.response.RoomResponse;
import com.org.hotel.enums.ReservationStatusEnum;
import com.org.hotel.model.Hotel;
import com.org.hotel.model.Reservation;
import com.org.hotel.model.Room;
import com.org.hotel.model.User;
import com.org.hotel.repository.HotelDao;
import com.org.hotel.repository.ReservationDao;
import com.org.hotel.repository.RoomDao;
import com.org.hotel.repository.UserDao;
import com.org.hotel.service.IReservationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class ReservationService implements IReservationService {
    private ReservationDao reservationDao;
    //    private CustomerDao customerDao;
    private HotelDao hotelDao;
    private RoomDao roomDao;
    private UserDao userDao;

    @Override
    public ReservationResponse createReservation(ReservationRequest reservationRequest, Long userId) throws Exception {
        log.info("Inside create reservation service.");
        Optional<User> userOptional = userDao.findById(userId);
        User user = userOptional.get();
        Optional<Hotel> hotel = hotelDao.findById(reservationRequest.getHotelId());
        if (!hotel.isPresent()) {
            throw new Exception("Hotel not found.");
        }
        Optional<Room> roomOpt = roomDao.findByIdAndHotelId(reservationRequest.getRoomId(),
                reservationRequest.getHotelId());
        if (!roomOpt.isPresent()) {
            throw new Exception("Hotel not found.");
        }
        List<Reservation> existingReservations = reservationDao.findByRoomAndStatusAndHotel(roomOpt.get(),
                ReservationStatusEnum.BOOKED, hotel.get());
        LocalDate checkIn = reservationRequest.getCheckinDate();
        LocalDate checkOut = reservationRequest.getCheckinDate();
        if (existingReservations != null && !existingReservations.isEmpty()) {
            for(Reservation r : existingReservations) {
                if ((checkIn.isEqual(r.getCheckinDate()) || (checkIn.isAfter(r.getCheckinDate()) && checkIn.isBefore(
                        r.getCheckinDate())) || (checkOut.isAfter(r.getCheckinDate()) && checkOut.isBefore(
                        r.getCheckinDate())))) {
                    throw new Exception("Room not available for this date.");
                }
            }
        }
        Reservation reservation = populateReservation(reservationRequest, hotel.get(), roomOpt.get(), user);
        reservationDao.save(reservation);
        ReservationResponse reservationResponse = ReservationResponse.builder().reservationId(reservation.getId())
                .build();
        return reservationResponse;
    }

    private Reservation populateReservation(ReservationRequest reservationRequest, Hotel hotel, Room room, User user) {
        return Reservation.builder().checkinDate(reservationRequest.getCheckinDate())
                .checkoutDate(reservationRequest.getCheckoutDate()).noOfPerson(reservationRequest.getNoOfPerson())
                .customer(user.getCustomer()).hotel(hotel).room(room).status(ReservationStatusEnum.BOOKED).build();
    }

    @Override
    public void cancelReservation(Long reservationId) throws Exception {
        Optional<Reservation> reservationOptional = reservationDao.findById(reservationId);
        if (!reservationOptional.isPresent()) {
            throw new Exception("Reservation not found with id " + reservationId);
        }
        reservationDao.updateReservationStatus(reservationId, ReservationStatusEnum.CANCELLED);
    }

    @Override
    public ReservationResponse getReservationById(Long reservationId) throws Exception {
        Optional<Reservation> reservationOptional = reservationDao.findById(reservationId);
        if (!reservationOptional.isPresent()) {
            throw new Exception("Reservation not found with id " + reservationId);
        }
        Reservation reservation = reservationOptional.get();
        ReservationResponse reservationResponse = ReservationResponse.builder().reservationId(reservation.getId())
                .status(reservation.getStatus()).checkinDate(reservation.getCheckinDate())
                .checkoutDate(reservation.getCheckoutDate())
                .bookingDetails(createHotelResponseForReservation(reservation)).build();
        return reservationResponse;
    }

    public HotelResponse createHotelResponseForReservation(Reservation reservation) {
        Room room = reservation.getRoom();
        return HotelResponse.builder().hotelId(reservation.getHotel().getId())
                .hotelTelephone(reservation.getHotel().getPhone()).name(reservation.getHotel().getName())
                .room(RoomResponse.builder().roomId(room.getId()).roomNumber(room.getRoomNumber())
                        .roomType(room.getType()).noOfPerson(room.getNoOfPerson()).price(room.getPrice()).build())
                .build();
    }

    @Override
    public List<ReservationResponse> getReservationList(Long userId) {
        List<ReservationResponse> reservationResponseList = new ArrayList<>();
        Optional<User> userOptional = userDao.findById(userId);
        User user = userOptional.get();
        List<Reservation> reservationList = reservationDao.findByCustomer(user.getCustomer());
        if (reservationList != null && !reservationList.isEmpty()) {
            reservationResponseList.addAll(
                    reservationList.stream().map(this::populateReservationResponse).collect(Collectors.toList()));
        }
        return reservationResponseList;
    }

    private ReservationResponse populateReservationResponse(Reservation reservation) {
        return ReservationResponse.builder().reservationId(reservation.getId()).status(reservation.getStatus())
                .checkinDate(reservation.getCheckinDate()).checkoutDate(reservation.getCheckoutDate()).build();
    }
}

