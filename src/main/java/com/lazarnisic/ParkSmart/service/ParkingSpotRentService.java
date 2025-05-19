package com.lazarnisic.ParkSmart.service;

import com.lazarnisic.ParkSmart.dto.ParkingSpotRentDTO;
import com.lazarnisic.ParkSmart.dto.ParkingSpotImageDTO;
import com.lazarnisic.ParkSmart.service.data.ParkingAccessData;
import com.lazarnisic.ParkSmart.service.data.ParkingSpotRentData;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public interface ParkingSpotRentService {
    List<ParkingSpotRentDTO> getAvailableParkingSpots(String cityName, LocalDateTime startTime, LocalDateTime endTime);

    List<ParkingSpotRentDTO> getRentParkingSpotsForCity(String cityName);

    List<ParkingSpotRentDTO> getSaleParkingSpotsForCity(String cityName);

    ParkingSpotRentDTO createParkingSpot(ParkingSpotRentData parkingSpotRentData);

    ParkingSpotImageDTO saveImage (Long parkingSpotId, MultipartFile file) throws IOException;

    ParkingSpotRentDTO createParkingAccess(Long parkingSpotId, ParkingAccessData parkingAccessData);
}
