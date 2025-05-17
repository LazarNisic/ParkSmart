package com.lazarnisic.ParkSmart.service;

import com.lazarnisic.ParkSmart.dto.ParkingSpotDTO;
import com.lazarnisic.ParkSmart.dto.ParkingSpotImageDTO;
import com.lazarnisic.ParkSmart.service.data.ParkingAccessData;
import com.lazarnisic.ParkSmart.service.data.ParkingSpotData;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public interface ParkingSpotService {
    List<ParkingSpotDTO> getAvailableParkingSpots(String cityName, LocalDateTime startTime, LocalDateTime endTime);

    List<ParkingSpotDTO> getRentParkingSpotsForCity(String cityName);

    List<ParkingSpotDTO> getSaleParkingSpotsForCity(String cityName);

    ParkingSpotDTO createParkingSpot(ParkingSpotData parkingSpotData);

    ParkingSpotImageDTO saveImage (Long parkingSpotId, MultipartFile file) throws IOException;

    ParkingSpotDTO createParkingAccess(Long parkingSpotId, ParkingAccessData parkingAccessData);
}
