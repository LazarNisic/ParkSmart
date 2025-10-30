package com.lazarnisic.ParkSmart.service.impl;

import com.lazarnisic.ParkSmart.dto.ReviewDTO;
import com.lazarnisic.ParkSmart.exception.ParkingSpotNotFound;
import com.lazarnisic.ParkSmart.exception.ReviewNotFound;
import com.lazarnisic.ParkSmart.mapper.ReviewMapper;
import com.lazarnisic.ParkSmart.mapper.UserMapper;
import com.lazarnisic.ParkSmart.model.ParkingSpotRent;
import com.lazarnisic.ParkSmart.model.Review;
import com.lazarnisic.ParkSmart.repository.ParkingSpotRentRepository;
import com.lazarnisic.ParkSmart.repository.ReviewRepository;
import com.lazarnisic.ParkSmart.service.ReviewService;
import com.lazarnisic.ParkSmart.service.UserService;
import com.lazarnisic.ParkSmart.service.data.ReviewData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReviewServiceImpl implements ReviewService {

    private final ParkingSpotRentRepository parkingSpotRentRepository;
    private final UserService userService;
    private final UserMapper userMapper;
    private final ReviewMapper reviewMapper;
    private final ReviewRepository reviewRepository;
    //Use for  granular evict
    private final CacheManager cacheManager;


    @Override
    @Transactional
    @Caching(evict = {
            // clean all pages
            @CacheEvict(cacheNames = "reviewsByParkingSpotPage", allEntries = true),
            @CacheEvict(cacheNames = "reviewsByOwnerPage", allEntries = true),
            // avg rating for parking - granular by key
            @CacheEvict(cacheNames = "avgRatingByParkingSpot", key = "#reviewData.parkingSpotId")
    })
    public ReviewDTO create(ReviewData reviewData) {

        ParkingSpotRent parkingSpotRent = parkingSpotRentRepository.findById(reviewData.getParkingSpotId()).
                orElseThrow(() -> new ParkingSpotNotFound(reviewData.getParkingSpotId()));

        Review review = new Review();
        review.setParkingSpotRent(parkingSpotRent);
        review.setUser(userMapper.toEntity(userService.getAuthenticatedUser()));
        int rating = reviewData.getRating();
        if (isInvalidRating(rating)) {
            throw new IllegalArgumentException("Rating must be an integer between 1 and 5.");
        }
        review.setRating(rating);
        review.setComment(reviewData.getComment());
        review.setTimestamp(LocalDateTime.now());

        return reviewMapper.toDto(reviewRepository.save(review));
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(
            cacheNames = "reviewsByParkingSpotPage",
            key = "'ps=' + #parkingSpotRentId + ',p=' + #pageable.pageNumber + ',s=' + #pageable.pageSize + ',sort=' + #pageable.sort.toString()",
            condition = "#parkingSpotRentId != null && #pageable != null"
    )
    public Page<ReviewDTO> findAllForParkingSpot(Long parkingSpotRentId, Pageable pageable) {
        Page<Review> reviewPage = reviewRepository.findByParkingSpotRent_Id(parkingSpotRentId, pageable);
        return reviewPage.map(reviewMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(
            cacheNames = "reviewsByOwnerPage",
            key = "'owner=' + #userId + ',p=' + #pageable.pageNumber + ',s=' + #pageable.pageSize + ',sort=' + #pageable.sort.toString()",
            condition = "#userId != null && #pageable != null"
    )
    public Page<ReviewDTO> findAllByOwner(Long userId, Pageable pageable) {
        Page<Review> reviewPage = reviewRepository.findByParkingSpotRent_Owner_Id(userId, pageable);
        return reviewPage.map(reviewMapper::toDto);
    }

    @Override
    @Transactional
    @Caching(evict = {
            @CacheEvict(cacheNames = "reviewsByParkingSpotPage", allEntries = true),
            @CacheEvict(cacheNames = "reviewsByOwnerPage", allEntries = true)
            // We evict avgRating in method, because we need the parkingSpotId that we get in the method
    })
    public ReviewDTO update(Long reviewId, ReviewData reviewData) {
        Review review = reviewRepository.findById(reviewId).
                orElseThrow(() -> new ReviewNotFound(reviewId));

        int rating = reviewData.getRating();
        if (isInvalidRating(rating)) {
            throw new IllegalArgumentException("Rating must be an integer between 1 and 5.");
        }
        review.setRating(rating);
        review.setComment(reviewData.getComment());
        review.setTimestamp(LocalDateTime.now());

        // Granular eviction of the average per parking spot
        Cache avgCache = cacheManager.getCache("avgRatingByParkingSpot");
        if (avgCache != null && review.getParkingSpotRent() != null) {
            avgCache.evict(review.getParkingSpotRent().getId());
        }

        return reviewMapper.toDto(reviewRepository.save(review));
    }

    @Override
    @Transactional
    @Caching(evict = {
            @CacheEvict(cacheNames = "reviewsByParkingSpotPage", allEntries = true),
            @CacheEvict(cacheNames = "reviewsByOwnerPage", allEntries = true)
            // We evict avgRating in method after we get parkingSpotId
    })
    public void delete(Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ReviewNotFound(reviewId));
        Long parkingSpotId = review.getParkingSpotRent() != null ? review.getParkingSpotRent().getId() : null;
        reviewRepository.deleteById(reviewId);

        // Granular eviction of the avg
        if (parkingSpotId != null) {
            Cache avgCache = cacheManager.getCache("avgRatingByParkingSpot");
            if (avgCache != null) {
                avgCache.evict(parkingSpotId);
            }
        }
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(
            cacheNames = "avgRatingByParkingSpot",
            key = "#parkingSpotRentId",
            condition = "#parkingSpotRentId != null"
    )
    public Double getAverageRatingForParkingSpot(Long parkingSpotRentId) {
        Double average = reviewRepository.findAverageRatingByParkingSpotRentId(parkingSpotRentId);
        return average != null ?
                BigDecimal.valueOf(average)
                        .setScale(2, RoundingMode.HALF_UP)
                        .doubleValue()
                : 0.0;
    }

    private boolean isInvalidRating(int rating) {
        return (rating <= 0 || rating >= 6);
    }
}
