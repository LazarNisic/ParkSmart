package com.lazarnisic.ParkSmart.service;

import com.lazarnisic.ParkSmart.dto.UserDTO;

public interface UserService {
    UserDTO getAuthenticatedUser();
    UserDTO findByUsername(String username);
}
