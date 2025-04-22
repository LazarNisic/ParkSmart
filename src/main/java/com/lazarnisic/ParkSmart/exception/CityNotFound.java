package com.lazarnisic.ParkSmart.exception;

import com.lazarnisic.ParkSmart.exception.handler.ErrorCode;

import java.util.Map;

public class CityNotFound extends ApplicationException {

    private static final String APP_ERROR_CODE = "CITY_NOT_FOUND";

    public CityNotFound(Long cityId) {
        super(String.format("City id=[%s] not found", cityId), ErrorCode.NOT_FOUND, APP_ERROR_CODE, Map.of("cityId", cityId));
    }

    public CityNotFound(String name) {
        super(String.format("City [%s] not found", name), ErrorCode.NOT_FOUND, APP_ERROR_CODE, Map.of("name", name));
    }

}
