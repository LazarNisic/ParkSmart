package com.lazarnisic.ParkSmart.controller;

import com.lazarnisic.ParkSmart.dto.ParkingSpotRentDTO;
import com.lazarnisic.ParkSmart.dto.ParkingSpotImageDTO;
import com.lazarnisic.ParkSmart.service.ParkingSpotRentService;
import com.lazarnisic.ParkSmart.service.data.ParkingAccessData;
import com.lazarnisic.ParkSmart.service.data.ParkingSpotRentData;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/parking-spot")
@RequiredArgsConstructor
@Validated
@Tag(name = "02 Parking Spot", description = "List of Parking Spot methods")
@SecurityRequirement(name = "bearerAuth")
public class ParkingSpotController {

    private final ParkingSpotRentService parkingSpotRentService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
    @GetMapping(value = "/available")
    public ResponseEntity<List<ParkingSpotRentDTO>> getAvailableParkingSpots(@RequestParam String city,
                                                                             @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
                                                                             @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime) {
        return new ResponseEntity<>(parkingSpotRentService.getAvailableParkingSpots(city, startTime, endTime), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
    @GetMapping(value = "/available-for-rent")
    public ResponseEntity<List<ParkingSpotRentDTO>> getRentParkingSpotsForCity(@RequestParam String city) {
        return new ResponseEntity<>(parkingSpotRentService.getRentParkingSpotsForCity(city), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
    @GetMapping(value = "/available-for-sale")
    public ResponseEntity<List<ParkingSpotRentDTO>> getSaleParkingSpotsForCity(@RequestParam String city) {
        return new ResponseEntity<>(parkingSpotRentService.getSaleParkingSpotsForCity(city), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
    @Operation(summary = "Create new parking spot", description = "Method for creating new parking spot")
    @PostMapping
    public ResponseEntity<ParkingSpotRentDTO> create(@Valid @RequestBody ParkingSpotRentData parkingSpotRentData) {
        return new ResponseEntity<>(parkingSpotRentService.createParkingSpot(parkingSpotRentData), HttpStatus.CREATED);
    }


    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
    @PostMapping("/{id}/upload-image")
    public ParkingSpotImageDTO uploadImage(@PathVariable Long id, @RequestParam("file") MultipartFile file) throws IOException {
        return parkingSpotRentService.saveImage(id, file);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
    @Operation(summary = "Create parking access", description = "Method for creating parking access features for parking spot")
    @PostMapping(value = "/{id}/create-parking-access")
    public ResponseEntity<ParkingSpotRentDTO> createParkingAccess(@PathVariable Long id, @Valid @RequestBody ParkingAccessData parkingAccessData) {
        return new ResponseEntity<>(parkingSpotRentService.createParkingAccess(id, parkingAccessData), HttpStatus.CREATED);
    }

}
