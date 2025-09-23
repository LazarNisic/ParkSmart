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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

    @Override
    public ReviewDTO create(ReviewData reviewData) {

        ParkingSpotRent parkingSpotRent = parkingSpotRentRepository.findById(reviewData.getParkingSpotId()).
                orElseThrow(() -> new ParkingSpotNotFound(reviewData.getParkingSpotId()));

        Review review = new Review();
        review.setParkingSpotRent(parkingSpotRent);
        review.setUser(userMapper.toEntity(userService.getAuthenticatedUser()));
        int rating = reviewData.getRating();
        if(!isValidRating(rating)){
            throw new IllegalArgumentException("Rating must be an integer between 1 and 5.");
        }
        review.setRating(rating);
        review.setComment(reviewData.getComment());
        review.setTimestamp(LocalDateTime.now());

        return reviewMapper.toDto(reviewRepository.save(review));
    }

    @Override
    public Page<ReviewDTO> findAllForParkingSpot(Long parkingSpotRentId, Pageable pageable) {
        Page<Review> reviewPage = reviewRepository.findByParkingSpotRent_Id(parkingSpotRentId, pageable);
        return reviewPage.map(reviewMapper::toDto);
    }

    @Override
    public Page<ReviewDTO> findAllByOwner(Long userId, Pageable pageable) {
        Page<Review> reviewPage = reviewRepository.findByParkingSpotRent_Owner_Id(userId, pageable);
        return reviewPage.map(reviewMapper::toDto);
    }

    @Override
    public ReviewDTO update(Long reviewId, ReviewData reviewData) {
        Review review = reviewRepository.findById(reviewId).
                orElseThrow(() -> new ReviewNotFound(reviewId));

        int rating = reviewData.getRating();
        if(!isValidRating(rating)){
            throw new IllegalArgumentException("Rating must be an integer between 1 and 5.");
        }
        review.setRating(rating);
        review.setComment(reviewData.getComment());
        review.setTimestamp(LocalDateTime.now());

        return reviewMapper.toDto(reviewRepository.save(review));
    }

    @Override
    public void delete(Long reviewId) {
        if (!reviewRepository.existsById(reviewId)) {
            throw new ReviewNotFound(reviewId);
        }
        reviewRepository.deleteById(reviewId);
    }


    @Override
    public Double getAverageRatingForParkingSpot(Long parkingSpotRentId) {
        Double average = reviewRepository.findAverageRatingByParkingSpotRentId(parkingSpotRentId);
        return average != null ?
                BigDecimal.valueOf(average)
                        .setScale(2, RoundingMode.HALF_UP)
                        .doubleValue()
                : 0.0;
    }


    private boolean isValidRating(int rating){
        return (rating > 0 && rating < 6);
    }
}
