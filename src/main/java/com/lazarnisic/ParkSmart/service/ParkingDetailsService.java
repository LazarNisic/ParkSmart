package com.lazarnisic.ParkSmart.service;

import com.lazarnisic.ParkSmart.model.Features;
import com.lazarnisic.ParkSmart.service.data.FeaturesData;

public interface ParkingDetailsService {
    Features createFeatures(FeaturesData data);

}
