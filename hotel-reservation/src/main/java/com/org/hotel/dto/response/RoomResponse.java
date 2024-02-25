package com.org.hotel.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.org.hotel.enums.RoomTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoomResponse {
    private Long roomId;
    private Integer roomNumber;
    @Enumerated(EnumType.STRING)
    private RoomTypeEnum roomType;
    private Integer noOfPerson;
    private BigDecimal price;
}

