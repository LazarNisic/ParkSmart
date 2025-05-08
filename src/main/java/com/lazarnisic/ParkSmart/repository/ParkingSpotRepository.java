package com.lazarnisic.ParkSmart.repository;

import com.lazarnisic.ParkSmart.enums.ListingType;
import com.lazarnisic.ParkSmart.model.ParkingSpot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ParkingSpotRepository extends JpaRepository<ParkingSpot, Long> {

    @Query("SELECT ps FROM ParkingSpot ps " +
            "WHERE ps.city.id = :cityId AND ps.available = true")
    List<ParkingSpot> findAvailableByLocation(@Param("cityId") Long cityId);

    @Query("SELECT ps FROM ParkingSpot ps " +
            "WHERE ps.city.id = :cityId AND ps.available = true AND ps.listingType = :listingType")
    List<ParkingSpot> findAvailableByLocationAndListingType(@Param("cityId") Long cityId, @Param("listingType") ListingType listingType);
}
