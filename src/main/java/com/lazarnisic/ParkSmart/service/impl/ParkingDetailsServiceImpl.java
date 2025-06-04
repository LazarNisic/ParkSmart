package com.lazarnisic.ParkSmart.service.impl;

import com.lazarnisic.ParkSmart.model.Features;
import com.lazarnisic.ParkSmart.repository.FeaturesRepository;
import com.lazarnisic.ParkSmart.service.ParkingDetailsService;
import com.lazarnisic.ParkSmart.service.data.FeaturesData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class ParkingDetailsServiceImpl implements ParkingDetailsService {

    private final FeaturesRepository featuresRepository;

    @Override
    public Features createFeatures(FeaturesData featuresData) {
        Features features = new Features();
        features.setSupportsSUV(featuresData.isSupportsSUV());
        features.setHasVideoSurveillance(featuresData.isHasVideoSurveillance());
        features.setAccessibleForDisabled(featuresData.isAccessibleForDisabled());
        features.setNightLighting(featuresData.isNightLighting());
        features.setHasEVChargingStation(featuresData.isHasEVChargingStation());
        features.setTimestamp(LocalDateTime.now());
        features.setTransportationService(featuresData.isTransportationService());
        features.setIndoor(featuresData.isIndoor());
        return featuresRepository.save(features);
    }



}
