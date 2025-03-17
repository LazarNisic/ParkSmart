package com.lazarnisic.ParkSmart.service;

import com.lazarnisic.ParkSmart.dto.ParkingSpotDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface ParkingSpotService {
    List<ParkingSpotDTO> getAvailableParkingSpots(String city, LocalDateTime startTime, LocalDateTime endTime);
}
