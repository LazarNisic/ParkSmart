package com.lazarnisic.ParkSmart.exception;

import com.lazarnisic.ParkSmart.exception.handler.ErrorCode;

import java.util.HashMap;

public class SessionExpired extends ApplicationException {
    private static final String APP_ERROR_CODE = "SESSION_EXPIRED";

    public SessionExpired() {
        super("Your session has expired", ErrorCode.UNAUTHORIZED, APP_ERROR_CODE, new HashMap<>());
    }
}
