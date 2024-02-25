package com.org.hotel.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HotelResponse {
    private Long hotelId;
    private String name;
    private String hotelTelephone;
    private List<RoomResponse> rooms;
    private RoomResponse room;
}
