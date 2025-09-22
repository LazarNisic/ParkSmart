package com.lazarnisic.ParkSmart.controller;

import com.lazarnisic.ParkSmart.dto.ReservationDTO;
import com.lazarnisic.ParkSmart.service.ReservationService;
import com.lazarnisic.ParkSmart.service.data.MonthlyReservationData;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
    @Operation(summary = "Create new monthly reservation", description = "Method for creating new monthly reservation")
    @PostMapping(value = "/monthly")
    public ResponseEntity<ReservationDTO> createMonthlyReservation(@Valid @RequestBody MonthlyReservationData monthlyReservationData) {
        return new ResponseEntity<>(reservationService.createMonthlyReservation(monthlyReservationData), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
    @Operation(summary = "Reservations for Parking spots owned by Authenticated User", description = "List of all Reservations for Parking spots owned by Authenticated User")
    @GetMapping(value = "/reserved-parking-spots-owned-by-user")
    public ResponseEntity<List<ReservationDTO>> getReservationsOwnedByAuthenticatedUser() {
        return new ResponseEntity<>(reservationService.getReservationsOwnedByAuthenticatedUser(), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
    @Operation(summary = "Reservations for Parking spots created by Authenticated User", description = "List of all Reservations for Parking spots created by Authenticated User")
    @GetMapping(value = "/reserved-parking-spots-created-by-user")
    public ResponseEntity<List<ReservationDTO>> getReservationsCreatedByAuthenticatedUser() {
        return new ResponseEntity<>(reservationService.getReservationsCreatedByAuthenticatedUser(), HttpStatus.OK);
    }
}
