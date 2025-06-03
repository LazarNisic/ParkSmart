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
    public Features createFeatures(FeaturesData data) {
        Features features = new Features();
        features.setSupportsSUV(data.isSupportsSUV());
        features.setHasVideoSurveillance(data.isHasVideoSurveillance());
        features.setAccessibleForDisabled(data.isAccessibleForDisabled());
        features.setNightLighting(data.isNightLighting());
        features.setHasEVChargingStation(data.isHasEVChargingStation());
        features.setTimestamp(LocalDateTime.now());
        return featuresRepository.save(features);
    }



}
