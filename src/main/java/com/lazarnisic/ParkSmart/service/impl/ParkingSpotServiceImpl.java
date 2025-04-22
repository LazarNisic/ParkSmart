package com.lazarnisic.ParkSmart.service.impl;

import com.lazarnisic.ParkSmart.dto.CityDTO;
import com.lazarnisic.ParkSmart.dto.ParkingSpotDTO;
import com.lazarnisic.ParkSmart.mapper.ParkingSpotMapper;
import com.lazarnisic.ParkSmart.mapper.UserMapper;
import com.lazarnisic.ParkSmart.model.City;
import com.lazarnisic.ParkSmart.model.ParkingSpot;
import com.lazarnisic.ParkSmart.model.Reservation;
import com.lazarnisic.ParkSmart.repository.CityRepository;
import com.lazarnisic.ParkSmart.repository.ParkingSpotRepository;
import com.lazarnisic.ParkSmart.repository.ReservationRepository;
import com.lazarnisic.ParkSmart.service.CityService;
import com.lazarnisic.ParkSmart.service.ParkingSpotService;
import com.lazarnisic.ParkSmart.service.UserService;
import com.lazarnisic.ParkSmart.service.data.ParkingSpotData;
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

    private final UserService userService;

    private final UserMapper userMapper;

    private final CityService cityService;

    private final CityRepository cityRepository;

    @Override
    public List<ParkingSpotDTO> getAvailableParkingSpots(String cityName, LocalDateTime startTime, LocalDateTime endTime) {

        //cityService or cityRepository???
        CityDTO city = cityService.findByName(cityName);

        List<ParkingSpot> availableSpots = parkingSpotRepository.findAvailableByLocation(city.getId());

        return parkingSpotMapper.toDto(availableSpots.stream()
                .filter(spot-> isSpotAvailable(spot, startTime, endTime))
                .toList());
    }

    @Override
    public ParkingSpotDTO createParkingSpot(ParkingSpotData parkingSpotData) {
        ParkingSpot parkingSpot = new ParkingSpot();
        parkingSpot.setOwner(userMapper.toEntity(userService.getAuthenticatedUser()));
        City city = new City();
        city.setName(parkingSpotData.getCity());
        city.setCountry(parkingSpotData.getCountry());
        City savedCity = cityRepository.save(city);
        parkingSpot.setCity(savedCity);
        parkingSpot.setAddress(parkingSpotData.getAddress());
        parkingSpot.setAvailable(true);
        parkingSpot.setPricePerHour(parkingSpotData.getPricePerHour());
        parkingSpot.setListingType(parkingSpotData.getListingType());
        return parkingSpotMapper.toDto(parkingSpotRepository.save(parkingSpot));
    }

    private boolean isSpotAvailable(ParkingSpot parkingSpot, LocalDateTime startTime, LocalDateTime endTime){
        List<Reservation> conflictingreservations = reservationRepository.findConflictingReservations(parkingSpot.getId(), startTime, endTime);
        return  conflictingreservations.isEmpty();
    }
}
