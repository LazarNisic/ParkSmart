package com.lazarnisic.ParkSmart.service;

import com.lazarnisic.ParkSmart.service.data.AuthenticateRequest;
import com.lazarnisic.ParkSmart.service.data.AuthenticateResponse;

public interface AuthService {
    AuthenticateResponse authenticate(AuthenticateRequest authenticateRequest);
}
