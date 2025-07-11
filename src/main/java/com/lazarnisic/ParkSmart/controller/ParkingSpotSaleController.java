package com.lazarnisic.ParkSmart.controller;

import com.lazarnisic.ParkSmart.dto.ParkingSpotSaleDTO;
import com.lazarnisic.ParkSmart.service.ParkingSpotSaleService;
import com.lazarnisic.ParkSmart.service.data.FeaturesData;
import com.lazarnisic.ParkSmart.service.data.ParkingSpotSaleData;
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
@RequestMapping(value = "/parking-spot-sale")
@RequiredArgsConstructor
@Validated
@Tag(name = "03 Parking Spot Sale", description = "List of Parking Spot Sale methods")
@SecurityRequirement(name = "bearerAuth")
public class ParkingSpotSaleController {

    private final ParkingSpotSaleService parkingSpotSaleService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
    @Operation(summary = "Create new parking spot for sale", description = "Method for creating new parking spot for sale")
    @PostMapping(value = "/create")
    public ResponseEntity<ParkingSpotSaleDTO> create(@Valid @RequestBody ParkingSpotSaleData parkingSpotSaleData) {
        return new ResponseEntity<>(parkingSpotSaleService.create(parkingSpotSaleData), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
    @Operation(summary = "Available parking spots for sale", description = "List of available parking spots for sale")
    @GetMapping(value = "/available-for-sale")
    public ResponseEntity<List<ParkingSpotSaleDTO>> getSaleParkingSpotsForCity(@RequestParam String cityName) {
        return new ResponseEntity<>(parkingSpotSaleService.getSaleParkingSpotsForCity(cityName), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
    @Operation(summary = "Create parking features", description = "Method for creating parking features for parking spot")
    @PostMapping(value = "/{id}/create-parking-features")
    public ResponseEntity<ParkingSpotSaleDTO> createParkingFeatures(@PathVariable Long id, @Valid @RequestBody FeaturesData featuresData) {
        return new ResponseEntity<>(parkingSpotSaleService.createParkingFeatures(id, featuresData), HttpStatus.CREATED);
    }
}
