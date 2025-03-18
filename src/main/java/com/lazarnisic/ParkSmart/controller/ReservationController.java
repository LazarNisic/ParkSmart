package com.lazarnisic.ParkSmart.controller;

import com.lazarnisic.ParkSmart.dto.ReservationDTO;
import com.lazarnisic.ParkSmart.service.ReservationService;
import com.lazarnisic.ParkSmart.service.data.ReservationData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/reservation")
@RequiredArgsConstructor
@Validated
@Tag(name = "03 Reservation", description = "List of Reservation methods")
@SecurityRequirement(name = "bearerAuth")
public class ReservationController {

    private final ReservationService reservationService;
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
    @Operation(summary = "Create new reservation", description = "Method for creating new reservation")
    @PostMapping
    public ResponseEntity<ReservationDTO> create(@Valid @RequestBody ReservationData reservationData) {
        return new ResponseEntity<>(reservationService.createReservation(reservationData), HttpStatus.CREATED);
    }
}
