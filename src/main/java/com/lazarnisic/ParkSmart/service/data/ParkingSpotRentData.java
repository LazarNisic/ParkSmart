package com.lazarnisic.ParkSmart.service.data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParkingSpotRentData {
    @NotBlank(message = "City cannot be null or empty.")
    private String city;
    @NotBlank(message = "Address cannot be null or empty.")
    private String address;
    @NotBlank(message = "Country cannot be null or empty.")
    private String country;
    @Positive(message = "Price per hour must be greater than zero.")
    private double pricePerHour;
    @Positive(message = "Minimal booking duration must be greater than zero.")
    private int minBookingDuration;
}
