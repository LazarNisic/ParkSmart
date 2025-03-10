package com.lazarnisic.ParkSmart.exception;

import com.lazarnisic.ParkSmart.exception.handler.ErrorCode;

import java.util.HashMap;

public class BadCredentials extends ApplicationException {

    private static final String APP_ERROR_CODE = "BAD_CREDENTIALS";
    private static final String MESSAGE = "Bad Credentials";

    public BadCredentials() {
        super(MESSAGE, ErrorCode.UNAUTHORIZED, APP_ERROR_CODE, new HashMap<>());
    }
}
