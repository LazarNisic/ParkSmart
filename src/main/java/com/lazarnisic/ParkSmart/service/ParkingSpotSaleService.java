package com.lazarnisic.ParkSmart.service;

import com.lazarnisic.ParkSmart.dto.ParkingSpotSaleDTO;
import com.lazarnisic.ParkSmart.service.data.ParkingSpotSaleData;

public interface ParkingSpotSaleService {
    ParkingSpotSaleDTO create(ParkingSpotSaleData parkingSpotSaleData);
}
