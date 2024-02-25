package com.org.hotel.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.org.hotel.enums.ReservationStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReservationResponse {
    private Long reservationId;
    private String customerName;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate checkinDate;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate checkoutDate;
    private ReservationStatusEnum status;
    private HotelResponse bookingDetails;
}
