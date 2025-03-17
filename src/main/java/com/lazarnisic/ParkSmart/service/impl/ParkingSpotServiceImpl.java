package com.lazarnisic.ParkSmart.service.impl;

import com.lazarnisic.ParkSmart.dto.ParkingSpotDTO;
import com.lazarnisic.ParkSmart.mapper.ParkingSpotMapper;
import com.lazarnisic.ParkSmart.model.ParkingSpot;
import com.lazarnisic.ParkSmart.model.Reservation;
import com.lazarnisic.ParkSmart.repository.ParkingSpotRepository;
import com.lazarnisic.ParkSmart.repository.ReservationRepository;
import com.lazarnisic.ParkSmart.service.ParkingSpotService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ParkingSpotServiceImpl implements ParkingSpotService {

    private final ParkingSpotRepository parkingSpotRepository;

    private final ReservationRepository reservationRepository;

    private final ParkingSpotMapper parkingSpotMapper;
    @Override
    public List<ParkingSpotDTO> getAvailableParkingSpots(String city, LocalDateTime startTime, LocalDateTime endTime) {

        List<ParkingSpot> availableSpots = parkingSpotRepository.findAvailableByLocation(city);

        return parkingSpotMapper.toDto(availableSpots.stream()
                .filter(spot-> isSpotAvailable(spot, startTime, endTime))
                .toList());
    }

    private boolean isSpotAvailable(ParkingSpot parkingSpot, LocalDateTime startTime, LocalDateTime endTime){
        List<Reservation> conflictingreservations = reservationRepository.findConflictingReservations(parkingSpot.getId(), startTime, endTime);
        return  conflictingreservations.isEmpty();
    }
}
