package com.lazarnisic.ParkSmart.repository;

import com.lazarnisic.ParkSmart.model.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface ReviewRepository extends JpaRepository<Review, Long> {
    Page<Review> findByParkingSpotRent_Id(Long parkingSpotRentId, Pageable pageable);

    Page<Review> findByParkingSpotRent_Owner_Id(Long userId, Pageable pageable);

    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.parkingSpotRent.id = :parkingSpotRentId")
    Double findAverageRatingByParkingSpotRentId(@Param("parkingSpotRentId") Long parkingSpotRentId);

}
