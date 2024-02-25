package com.org.hotel.model;

import com.org.hotel.enums.ReservationStatusEnum;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private LocalDate checkinDate;
    private LocalDate checkoutDate;
    private Integer noOfPerson;
    @ManyToOne(fetch = FetchType.EAGER)
    private Room room;
    @ManyToOne(fetch = FetchType.EAGER)
    private Hotel hotel;
    @Enumerated(EnumType.STRING)
    private ReservationStatusEnum status;
    @ManyToOne(fetch = FetchType.EAGER)
    private Customer customer;
}
