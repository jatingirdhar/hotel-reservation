package com.org.hotel.service;

import com.org.hotel.dto.request.ReservationRequest;
import com.org.hotel.dto.response.ReservationResponse;

import java.util.List;

public interface IReservationService {
    public ReservationResponse createReservation(ReservationRequest reservationRequest, Long userId) throws Exception;

    public void cancelReservation(Long reservationId) throws Exception;


    public ReservationResponse getReservationById(Long reservationId) throws Exception;

    List<ReservationResponse> getReservationList(Long userId);
}
