package com.lazarnisic.ParkSmart.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class ParkingSpotSaleDTO {
    private Long id;
    private CityDTO city;
    private String address;
    private double price;
    private LocalDateTime timestamp;
}
