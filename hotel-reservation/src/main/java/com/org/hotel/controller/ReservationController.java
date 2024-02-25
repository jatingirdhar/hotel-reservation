package com.org.hotel.controller;

import com.org.hotel.config.CustomUserDetails;
import com.org.hotel.dto.request.ReservationRequest;
import com.org.hotel.dto.response.HotelResponse;
import com.org.hotel.dto.response.ReservationResponse;
import com.org.hotel.service.IReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/reservation")
public class ReservationController {
    @Autowired
    IReservationService reservationService;

    @PostMapping("/book")
    public ResponseEntity<ReservationResponse> createReservation(
            @Valid @RequestBody ReservationRequest reservationRequest, Authentication authentication) throws Exception {
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        Long userId = customUserDetails.getUserId();
        return new ResponseEntity<>(reservationService.createReservation(reservationRequest, userId),
                HttpStatus.CREATED);
    }

    @PutMapping("/list")
    public ResponseEntity<List<ReservationResponse>> getHotelList(Authentication authentication) {
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        Long userId = customUserDetails.getUserId();
        return new ResponseEntity<>(reservationService.getReservationList(userId), HttpStatus.OK);
    }

    @PutMapping("/cancel/id/{reservationId}")
    public ResponseEntity<Void> cancelReservation(@PathVariable("reservationId") Long reservationId) throws Exception {
        reservationService.cancelReservation(reservationId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("/id/{reservationId}")
    public ResponseEntity<ReservationResponse> getReservationById(@PathVariable("reservationId") Long reservationId)
            throws Exception {
        return new ResponseEntity<>(reservationService.getReservationById(reservationId), HttpStatus.OK);
    }
}
