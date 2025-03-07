package com.lazarnisic.ParkSmart.service;

import com.lazarnisic.ParkSmart.dto.UserDTO;
import com.lazarnisic.ParkSmart.service.data.AuthenticateRequest;
import com.lazarnisic.ParkSmart.service.data.AuthenticateResponse;
import com.lazarnisic.ParkSmart.service.data.UserData;

public interface AuthService {
    AuthenticateResponse authenticate(AuthenticateRequest authenticateRequest);

    UserDTO register(UserData userData);
}
