package com.lazarnisic.ParkSmart.exception;

import com.lazarnisic.ParkSmart.exception.handler.ErrorCode;

import java.util.Map;

public class ReviewNotFound extends ApplicationException {

    private static final String APP_ERROR_CODE = "REVIEW_NOT_FOUND";

    public ReviewNotFound(Long reviewId) {
        super(String.format("Review id=[%s] not found", reviewId), ErrorCode.NOT_FOUND, APP_ERROR_CODE, Map.of("reviewId", reviewId));
    }

}
