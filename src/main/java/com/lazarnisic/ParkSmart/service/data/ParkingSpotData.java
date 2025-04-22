package com.lazarnisic.ParkSmart.service.data;

import com.lazarnisic.ParkSmart.enums.ListingType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParkingSpotData {
    @NotBlank(message = "City cannot be null or empty.")
    private String city;
    @NotBlank(message = "Address cannot be null or empty.")
    private String address;
    @NotBlank(message = "Country cannot be null or empty.")
    private String country;
    @Positive(message = "Price per hour must be greater than zero.")
    private double pricePerHour;
    @NotNull(message = "Listing type cannot be null.")
    private ListingType listingType;
}
