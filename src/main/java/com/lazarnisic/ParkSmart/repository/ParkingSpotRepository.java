package com.lazarnisic.ParkSmart.repository;

import com.lazarnisic.ParkSmart.model.ParkingSpot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ParkingSpotRepository extends JpaRepository<ParkingSpot, Long> {

    @Query("SELECT ps FROM ParkingSpot ps " +
            "WHERE ps.city =: city AND ps.isAvailable = true")
    List<ParkingSpot> findAvailableByLocation(@Param("city") String city);
}
