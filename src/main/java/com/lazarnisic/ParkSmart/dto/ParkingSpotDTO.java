package com.lazarnisic.ParkSmart.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.lazarnisic.ParkSmart.enums.ListingType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class ParkingSpotDTO {
    private Long id;
    private CityDTO city;
    private String address;
    private boolean available;
    private double pricePerHour;
    @Enumerated(EnumType.STRING)
    private ListingType listingType;
}
