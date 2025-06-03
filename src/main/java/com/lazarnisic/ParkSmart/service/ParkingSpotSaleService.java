package com.lazarnisic.ParkSmart.service;

import com.lazarnisic.ParkSmart.dto.ParkingSpotSaleDTO;
import com.lazarnisic.ParkSmart.service.data.FeaturesData;
import com.lazarnisic.ParkSmart.service.data.ParkingSpotSaleData;

import java.util.List;

public interface ParkingSpotSaleService {
    ParkingSpotSaleDTO create(ParkingSpotSaleData parkingSpotSaleData);

    List<ParkingSpotSaleDTO> getSaleParkingSpotsForCity(String cityName);

    ParkingSpotSaleDTO createParkingFeatures(Long parkingSpotId, FeaturesData featuresData);
}
