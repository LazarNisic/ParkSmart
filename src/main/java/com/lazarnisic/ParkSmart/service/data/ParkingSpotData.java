package com.lazarnisic.ParkSmart.service.data;

import com.lazarnisic.ParkSmart.enums.ListingType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParkingSpotData {
    private String city;
    private String address;
    private double pricePerHour;
    private ListingType listingType;
}
