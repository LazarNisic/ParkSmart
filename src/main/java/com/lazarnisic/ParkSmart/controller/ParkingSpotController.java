package com.lazarnisic.ParkSmart.controller;

import com.lazarnisic.ParkSmart.dto.ParkingSpotDTO;
import com.lazarnisic.ParkSmart.service.ParkingSpotService;
import com.lazarnisic.ParkSmart.service.data.ParkingSpotData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/parking-spot")
@RequiredArgsConstructor
@Validated
@Tag(name = "02 Parking Spot", description = "List of Parking Spot methods")
@SecurityRequirement(name = "bearerAuth")
public class ParkingSpotController {

    private final ParkingSpotService parkingSpotService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
    @GetMapping(value = "/available")
    public ResponseEntity<List<ParkingSpotDTO>> getAvailableParkingSpots(@RequestParam String city,
                                                                        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime startTime,
                                                                        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime endTime) {
        return new ResponseEntity<>(parkingSpotService.getAvailableParkingSpots(city, startTime, endTime), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
    @Operation(summary = "Create new parking spot", description = "Method for creating new parking spot")
    @PostMapping
    public ResponseEntity<ParkingSpotDTO> create(@Valid @RequestBody ParkingSpotData parkingSpotData) {
        return new ResponseEntity<>(parkingSpotService.createParkingSpot(parkingSpotData), HttpStatus.CREATED);
    }
}
