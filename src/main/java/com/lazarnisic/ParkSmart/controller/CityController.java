package com.lazarnisic.ParkSmart.controller;

import com.lazarnisic.ParkSmart.dto.CityDTO;
import com.lazarnisic.ParkSmart.service.CityService;
import com.lazarnisic.ParkSmart.service.data.CityData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/city")
@RequiredArgsConstructor
@Validated
@Tag(name = "04 City", description = "List of City methods")
@SecurityRequirement(name = "bearerAuth")
public class CityController {

    private final CityService cityService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
    @GetMapping(value = "/find-all")
    public ResponseEntity<List<CityDTO>> findAll() {
        return new ResponseEntity<>(cityService.findAll(), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
    @GetMapping(value = "/find-by-name")
    public ResponseEntity<CityDTO> findByName(@RequestParam(defaultValue = StringUtils.EMPTY) String name) {
        return new ResponseEntity<>(cityService.findByName(name), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
    @Operation(summary = "Create new city", description = "Method for creating new city")
    @PostMapping
    public ResponseEntity<CityDTO> create(@Valid @RequestBody CityData cityData) {
        return new ResponseEntity<>(cityService.create(cityData), HttpStatus.CREATED);
    }
}
