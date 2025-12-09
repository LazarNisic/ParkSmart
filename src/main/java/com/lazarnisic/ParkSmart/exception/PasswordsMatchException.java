package com.lazarnisic.ParkSmart.exception;

import com.lazarnisic.ParkSmart.exception.handler.ErrorCode;

public class PasswordsMatchException extends ApplicationException {

    private static final String APP_ERROR_CODE = "PASSWORDS_MATCH";

    private static final String MESSAGE = "Passwords match. Select different new password";

    public PasswordsMatchException() {
        super(MESSAGE, ErrorCode.BAD_REQUEST, APP_ERROR_CODE);
    }
}
