package com.lazarnisic.ParkSmart.exception;

import com.lazarnisic.ParkSmart.exception.handler.ErrorCode;

import java.util.Map;

public class ReservationDurationException extends ApplicationException {

    private static final String APP_ERROR_CODE = "MINIMAL_BOOKING_DURATION_NOT_MET";

    public ReservationDurationException(int minBookingDuration) {
        super(String.format("Minimal booking duration is %s hours", minBookingDuration), ErrorCode.BAD_REQUEST, APP_ERROR_CODE, Map.of("minBookingDuration", minBookingDuration));
    }

    public ReservationDurationException(String message) {
        super(message, ErrorCode.BAD_REQUEST, APP_ERROR_CODE, Map.of("message", message));
    }

}
