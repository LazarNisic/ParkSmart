package com.lazarnisic.ParkSmart.service;

import com.lazarnisic.ParkSmart.dto.ReviewDTO;
import com.lazarnisic.ParkSmart.service.data.ReviewData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ReviewService {
    ReviewDTO create(ReviewData reviewData);

    Page<ReviewDTO> findAllForParkingSpot(Long parkingSpotRentId, Pageable pageable);

    Page<ReviewDTO> findAllByOwner(Long userId, Pageable pageable);

    ReviewDTO update(Long reviewId, ReviewData reviewData);

    void delete(Long reviewId);

    Double getAverageRatingForParkingSpot(Long parkingSpotRentId);
}
