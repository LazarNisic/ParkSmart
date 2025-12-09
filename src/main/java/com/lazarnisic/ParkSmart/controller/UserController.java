package com.lazarnisic.ParkSmart.controller;

import com.lazarnisic.ParkSmart.dto.UserDTO;
import com.lazarnisic.ParkSmart.service.UserService;
import com.lazarnisic.ParkSmart.service.data.ChangePasswordRequest;
import com.lazarnisic.ParkSmart.service.data.UserData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/users")
@RequiredArgsConstructor
@Validated
@Tag(name = "01_Users", description = "List of User interfaces")
@SecurityRequirement(name = "bearerAuth")
public class UserController {

    private final UserService userService;

    @GetMapping(value = "/authenticated-user")
    public ResponseEntity<UserDTO> getAuthenticatedUserDetails() {
        return new ResponseEntity<>(userService.getAuthenticatedUser(), HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping(value = "/change-password")
    @Operation(summary = "Change password", description = "Method for changing password")
    public ResponseEntity<UserDTO> changePassword(@Valid @RequestBody ChangePasswordRequest changePasswordRequest){
        return new ResponseEntity<>(userService.changePassword(changePasswordRequest), HttpStatus.OK);
    }
}
