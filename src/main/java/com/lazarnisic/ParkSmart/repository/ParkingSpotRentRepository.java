package com.lazarnisic.ParkSmart.repository;

import com.lazarnisic.ParkSmart.model.ParkingSpotRent;
import com.lazarnisic.ParkSmart.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ParkingSpotRentRepository extends JpaRepository<ParkingSpotRent, Long> {

    @Query("SELECT ps FROM ParkingSpotRent ps " +
            "WHERE ps.city.id = :cityId AND ps.available = true")
    List<ParkingSpotRent> findAvailableByLocation(@Param("cityId") Long cityId);

    List<ParkingSpotRent> findAllByCity_NameAndAvailableTrue(String cityName);
    List<ParkingSpotRent> findByOwner(User owner);
}
