package com.org.hotel.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.Valid;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Valid
public class ReservationRequest {
    @NotNull
    private Long hotelId;
    @NotNull
    private Long roomId;
    @NotNull
    private Integer noOfPerson;
    @NotNull
    @FutureOrPresent
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate checkinDate;
    @NotNull
    @FutureOrPresent
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate checkoutDate;
}
