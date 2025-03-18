package com.lazarnisic.ParkSmart.exception;

import com.lazarnisic.ParkSmart.exception.handler.ErrorCode;

import java.util.Map;

public class ParkingSpotNotFound extends ApplicationException {

    private static final String APP_ERROR_CODE = "PARKING_SPOT_NOT_FOUND";

    public ParkingSpotNotFound(Long parkingSpotId) {
        super(String.format("Parking spot id=[%s] not found", parkingSpotId), ErrorCode.NOT_FOUND, APP_ERROR_CODE, Map.of("parkingSpotId", parkingSpotId));
    }

}
