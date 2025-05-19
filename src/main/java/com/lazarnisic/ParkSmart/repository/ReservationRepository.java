package com.lazarnisic.ParkSmart.repository;

import com.lazarnisic.ParkSmart.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("SELECT r FROM Reservation r " +
            "WHERE r.parkingSpotRent.id = :parkingSpotRentId " +
            "AND (r.startTime < :endTime AND r.endTime > :startTime)")
    List<Reservation> findConflictingReservations(
            @Param("parkingSpotRentId") Long parkingSpotRentId,
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime
            );
}
