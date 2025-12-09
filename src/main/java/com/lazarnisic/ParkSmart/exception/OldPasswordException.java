package com.lazarnisic.ParkSmart.exception;

import com.lazarnisic.ParkSmart.exception.handler.ErrorCode;

public class OldPasswordException extends ApplicationException {

    private static final String APP_ERROR_CODE = "NOT_OLD_PASSWORD";

    private static final String MESSAGE = "User password history exception. Not old password.";

    public OldPasswordException() {
        super(MESSAGE, ErrorCode.BAD_REQUEST, APP_ERROR_CODE);
    }
}
