package com.lazarnisic.ParkSmart.service;

import com.lazarnisic.ParkSmart.dto.UserDTO;
import com.lazarnisic.ParkSmart.service.data.ChangePasswordRequest;

public interface UserService {
    UserDTO getAuthenticatedUser();
    UserDTO findByUsername(String username);
    UserDTO changePassword(ChangePasswordRequest changePasswordRequest);
}
