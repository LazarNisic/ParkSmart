package com.lazarnisic.ParkSmart.controller;

import com.lazarnisic.ParkSmart.dto.ReviewDTO;
import com.lazarnisic.ParkSmart.service.ReviewService;
import com.lazarnisic.ParkSmart.service.data.CustomPageRequest;
import com.lazarnisic.ParkSmart.service.data.ReviewData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/review")
@RequiredArgsConstructor
@Validated
@Tag(name = "06 Review", description = "List of Review methods")
@SecurityRequirement(name = "bearerAuth")
public class ReviewController {

    private final ReviewService reviewService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
    @Operation(summary = "Create new review", description = "Method for creating new review for Parking Spot")
    @PostMapping(value = "/create")
    public ResponseEntity<ReviewDTO> create(@RequestBody ReviewData reviewData) {
        return new ResponseEntity<>(reviewService.create(reviewData), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
    @GetMapping(value = "/find-all-for-parking-spot")
    public ResponseEntity<Page<ReviewDTO>> findAllForParkingSpot(Long parkingSpotRentId, @ParameterObject CustomPageRequest customPageRequest) {
        Pageable pageable = customPageRequest.toPageable();
        return new ResponseEntity<>(reviewService.findAllForParkingSpot(parkingSpotRentId, pageable), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
    @GetMapping(value = "/find-all-by-owner")
    public ResponseEntity<Page<ReviewDTO>> findAllByOwner(Long userId, @ParameterObject CustomPageRequest customPageRequest) {
        Pageable pageable = customPageRequest.toPageable();
        return new ResponseEntity<>(reviewService.findAllByOwner(userId, pageable), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
    @Operation(summary = "Update review", description = "Method for updating review for Parking Spot")
    @PutMapping(value = "/update/{id}")
    public ResponseEntity<ReviewDTO> update(@PathVariable Long id, @RequestBody ReviewData reviewData) {
        return new ResponseEntity<>(reviewService.update(id, reviewData), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        reviewService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
