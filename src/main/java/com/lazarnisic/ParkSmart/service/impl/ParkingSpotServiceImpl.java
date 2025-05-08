package com.lazarnisic.ParkSmart.service.impl;

import com.lazarnisic.ParkSmart.dto.CityDTO;
import com.lazarnisic.ParkSmart.dto.ParkingSpotDTO;
import com.lazarnisic.ParkSmart.dto.ParkingSpotImageDTO;
import com.lazarnisic.ParkSmart.enums.ListingType;
import com.lazarnisic.ParkSmart.exception.ParkingSpotNotFound;
import com.lazarnisic.ParkSmart.mapper.ParkingSpotImageMapper;
import com.lazarnisic.ParkSmart.mapper.ParkingSpotMapper;
import com.lazarnisic.ParkSmart.mapper.UserMapper;
import com.lazarnisic.ParkSmart.model.City;
import com.lazarnisic.ParkSmart.model.ParkingSpot;
import com.lazarnisic.ParkSmart.model.ParkingSpotImage;
import com.lazarnisic.ParkSmart.model.Reservation;
import com.lazarnisic.ParkSmart.repository.CityRepository;
import com.lazarnisic.ParkSmart.repository.ParkingSpotImageRepository;
import com.lazarnisic.ParkSmart.repository.ParkingSpotRepository;
import com.lazarnisic.ParkSmart.repository.ReservationRepository;
import com.lazarnisic.ParkSmart.service.CityService;
import com.lazarnisic.ParkSmart.service.ParkingSpotService;
import com.lazarnisic.ParkSmart.service.UserService;
import com.lazarnisic.ParkSmart.service.data.ParkingSpotData;
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
public class ParkingSpotServiceImpl implements ParkingSpotService {

    private final ParkingSpotRepository parkingSpotRepository;

    private final ReservationRepository reservationRepository;

    private final ParkingSpotMapper parkingSpotMapper;

    private final UserService userService;

    private final UserMapper userMapper;

    private final CityService cityService;

    private final CityRepository cityRepository;

    private final ParkingSpotImageRepository parkingSpotImageRepository;

    private final ParkingSpotImageMapper parkingSpotImageMapper;

    @Override
    public List<ParkingSpotDTO> getAvailableParkingSpots(String cityName, LocalDateTime startTime, LocalDateTime endTime) {

        //cityService or cityRepository???
        CityDTO city = cityService.findByName(cityName);

        List<ParkingSpot> availableSpots = parkingSpotRepository.findAvailableByLocation(city.getId());

        return parkingSpotMapper.toDto(availableSpots.stream()
                .filter(spot -> isSpotAvailable(spot, startTime, endTime))
                .toList());
    }

    @Override
    public List<ParkingSpotDTO> getRentParkingSpotsForCity(String cityName) {
        //cityService or cityRepository???
        CityDTO city = cityService.findByName(cityName);
        List<ParkingSpot> availableSpotsForRent = parkingSpotRepository
                .findAvailableByLocationAndListingType(city.getId(), ListingType.RENT);
        return parkingSpotMapper.toDto(availableSpotsForRent);
    }

    @Override
    public List<ParkingSpotDTO> getSaleParkingSpotsForCity(String cityName) {
        //cityService or cityRepository???
        CityDTO city = cityService.findByName(cityName);
        List<ParkingSpot> availableSpotsForRent = parkingSpotRepository
                .findAvailableByLocationAndListingType(city.getId(), ListingType.SALE);
        return parkingSpotMapper.toDto(availableSpotsForRent);
    }

    @Override
    public ParkingSpotDTO createParkingSpot(ParkingSpotData parkingSpotData) {
        ParkingSpot parkingSpot = new ParkingSpot();
        parkingSpot.setOwner(userMapper.toEntity(userService.getAuthenticatedUser()));
        City city = new City();
        city.setName(parkingSpotData.getCity());
        city.setCountry(parkingSpotData.getCountry());
        city.setTimestamp(LocalDateTime.now());
        City savedCity = cityRepository.save(city);
        parkingSpot.setCity(savedCity);
        parkingSpot.setAddress(parkingSpotData.getAddress());
        parkingSpot.setAvailable(true);
        parkingSpot.setPricePerHour(parkingSpotData.getPricePerHour());
        parkingSpot.setListingType(parkingSpotData.getListingType());
        parkingSpot.setTimestamp(LocalDateTime.now());
        return parkingSpotMapper.toDto(parkingSpotRepository.save(parkingSpot));
    }

    @Override
    public ParkingSpotImageDTO saveImage(Long parkingSpotId, MultipartFile file) throws IOException {
        ParkingSpot parkingSpot = parkingSpotRepository.findById(parkingSpotId)
                .orElseThrow(() -> new ParkingSpotNotFound(parkingSpotId));

        String imageUrl = saveFileToStorage(file);

        ParkingSpotImage parkingSpotImage = new ParkingSpotImage();
        parkingSpotImage.setParkingSpot(parkingSpot);
        parkingSpotImage.setImageUrl(imageUrl);
        parkingSpotImage.setTimestamp(LocalDateTime.now());
        return parkingSpotImageMapper.toDto(parkingSpotImageRepository.save(parkingSpotImage));
    }

    private boolean isSpotAvailable(ParkingSpot parkingSpot, LocalDateTime startTime, LocalDateTime endTime) {
        List<Reservation> conflictingreservations = reservationRepository.findConflictingReservations(parkingSpot.getId(), startTime, endTime);
        return conflictingreservations.isEmpty();
    }


    private String saveFileToStorage(MultipartFile file) throws IOException {
        // TODO: Implement file saving logic here
        log.info("Image saved!");
        return "url_of_saved_image";
    }

}
