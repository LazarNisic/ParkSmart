package com.lazarnisic.ParkSmart.service.impl;

import com.lazarnisic.ParkSmart.dto.CityDTO;
import com.lazarnisic.ParkSmart.dto.ParkingSpotRentDTO;
import com.lazarnisic.ParkSmart.dto.ParkingSpotImageDTO;
import com.lazarnisic.ParkSmart.exception.ParkingSpotNotFound;
import com.lazarnisic.ParkSmart.mapper.ParkingSpotImageMapper;
import com.lazarnisic.ParkSmart.mapper.ParkingSpotRentMapper;
import com.lazarnisic.ParkSmart.mapper.UserMapper;
import com.lazarnisic.ParkSmart.model.*;
import com.lazarnisic.ParkSmart.repository.*;
import com.lazarnisic.ParkSmart.service.CityService;
import com.lazarnisic.ParkSmart.service.ParkingSpotRentService;
import com.lazarnisic.ParkSmart.service.UserService;
import com.lazarnisic.ParkSmart.service.data.ParkingAccessData;
import com.lazarnisic.ParkSmart.service.data.ParkingSpotRentData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ParkingSpotRentServiceImpl implements ParkingSpotRentService {

    private final ParkingSpotRentRepository parkingSpotRentRepository;

    private final ReservationRepository reservationRepository;

    private final ParkingSpotRentMapper parkingSpotRentMapper;

    private final UserService userService;

    private final UserMapper userMapper;

    private final CityService cityService;

    private final ParkingSpotImageRepository parkingSpotImageRepository;

    private final ParkingSpotImageMapper parkingSpotImageMapper;

    private final ParkingAccessRepository parkingAccessRepository;

    @Override
    public List<ParkingSpotRentDTO> getAvailableParkingSpots(String cityName, LocalDateTime startTime, LocalDateTime endTime) {

        CityDTO city = cityService.findByName(cityName);

        List<ParkingSpotRent> availableSpots = parkingSpotRentRepository.findAvailableByLocation(city.getId());

        return parkingSpotRentMapper.toDto(availableSpots.stream()
                .filter(spot -> isSpotAvailable(spot, startTime, endTime))
                .toList());
    }

    @Override
    public List<ParkingSpotRentDTO> getRentParkingSpotsForCity(String cityName) {
        List<ParkingSpotRent> availableSpotsForRent = parkingSpotRentRepository.findAllByCity_NameAndAvailableTrue(cityName);
        return parkingSpotRentMapper.toDto(availableSpotsForRent);
    }

    @Override
    public ParkingSpotRentDTO createParkingSpot(ParkingSpotRentData parkingSpotRentData) {
        ParkingSpotRent parkingSpotRent = new ParkingSpotRent();
        parkingSpotRent.setOwner(userMapper.toEntity(userService.getAuthenticatedUser()));
        City city = cityService.findOrCreate(parkingSpotRentData.getCity(), parkingSpotRentData.getCountry());
        parkingSpotRent.setCity(city);
        parkingSpotRent.setAddress(parkingSpotRentData.getAddress());
        parkingSpotRent.setAvailable(true);
        parkingSpotRent.setPricePerHour(parkingSpotRentData.getPricePerHour());
        parkingSpotRent.setTimestamp(LocalDateTime.now());
        return parkingSpotRentMapper.toDto(parkingSpotRentRepository.save(parkingSpotRent));
    }

    @Override
    public ParkingSpotImageDTO saveImage(Long parkingSpotId, MultipartFile file) throws IOException {
        ParkingSpotRent parkingSpotRent = parkingSpotRentRepository.findById(parkingSpotId)
                .orElseThrow(() -> new ParkingSpotNotFound(parkingSpotId));

        String imageUrl = saveFileToStorage(file);

        ParkingSpotImage parkingSpotImage = new ParkingSpotImage();
        parkingSpotImage.setParkingSpotRent(parkingSpotRent);
        parkingSpotImage.setImageUrl(imageUrl);
        parkingSpotImage.setTimestamp(LocalDateTime.now());
        return parkingSpotImageMapper.toDto(parkingSpotImageRepository.save(parkingSpotImage));
    }

    @Override
    public ParkingSpotRentDTO createParkingAccess(Long parkingSpotId, ParkingAccessData parkingAccessData) {
        ParkingSpotRent parkingSpotRent = parkingSpotRentRepository.findById(parkingSpotId)
                .orElseThrow(() -> new ParkingSpotNotFound(parkingSpotId));

        ParkingAccess parkingAccess = new ParkingAccess();
        parkingAccess.setAccessType(parkingAccessData.getAccessType());
        parkingAccess.setNumberOfAccesses(parkingAccessData.getNumberOfAccesses());
        parkingAccess.setAccessTimeStart(parkingAccessData.getAccessTimeStart());
        parkingAccess.setAccessTimeEnd(parkingAccessData.getAccessTimeEnd());
        parkingAccess.setTimestamp(LocalDateTime.now());

        parkingSpotRent.setParkingAccess(parkingAccessRepository.save(parkingAccess));

        return parkingSpotRentMapper.toDto(parkingSpotRentRepository.save(parkingSpotRent));
    }

    private boolean isSpotAvailable(ParkingSpotRent parkingSpotRent, LocalDateTime startTime, LocalDateTime endTime) {
        List<Reservation> conflictingreservations = reservationRepository.findConflictingReservations(parkingSpotRent.getId(), startTime, endTime);
        return conflictingreservations.isEmpty();
    }


    private String saveFileToStorage(MultipartFile file) throws IOException {
        // TODO: Implement file saving logic here
        log.info("Image saved!");
        return "url_of_saved_image";
    }

}
