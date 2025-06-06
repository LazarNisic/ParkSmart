package com.lazarnisic.ParkSmart.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class ParkingSpotRentDTO {
    private Long id;
    private CityDTO city;
    private String address;
    private boolean available;
    private double pricePerHour;
    private int minBookingDuration;
    private LocalDateTime timestamp;
    private ParkingAccessDTO parkingAccess;
    private FeaturesDTO features;
}
