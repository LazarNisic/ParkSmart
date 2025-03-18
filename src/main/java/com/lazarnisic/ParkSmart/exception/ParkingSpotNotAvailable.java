package com.lazarnisic.ParkSmart.exception;

import com.lazarnisic.ParkSmart.exception.handler.ErrorCode;

import java.util.Map;

public class ParkingSpotNotAvailable extends ApplicationException {

    private static final String APP_ERROR_CODE = "PARKING_SPOT_NOT_AVAILABLE";

    public ParkingSpotNotAvailable(Long parkingSpotId) {
        super(String.format("Parking spot id=[%s] not available", parkingSpotId), ErrorCode.BAD_REQUEST, APP_ERROR_CODE, Map.of("parkingSpotId", parkingSpotId));
    }

    public ParkingSpotNotAvailable(String message) {
        super(String.format(message), ErrorCode.BAD_REQUEST, APP_ERROR_CODE, Map.of("message", message));
    }
}
