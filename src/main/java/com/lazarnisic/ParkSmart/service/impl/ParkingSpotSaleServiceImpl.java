package com.lazarnisic.ParkSmart.service.impl;

import com.lazarnisic.ParkSmart.dto.ParkingSpotSaleDTO;
import com.lazarnisic.ParkSmart.exception.ParkingSpotNotFound;
import com.lazarnisic.ParkSmart.mapper.ParkingSpotSaleMapper;
import com.lazarnisic.ParkSmart.mapper.UserMapper;
import com.lazarnisic.ParkSmart.model.City;
import com.lazarnisic.ParkSmart.model.Features;
import com.lazarnisic.ParkSmart.model.ParkingSpotSale;
import com.lazarnisic.ParkSmart.repository.ParkingSpotSaleRepository;
import com.lazarnisic.ParkSmart.service.CityService;
import com.lazarnisic.ParkSmart.service.ParkingDetailsService;
import com.lazarnisic.ParkSmart.service.ParkingSpotSaleService;
import com.lazarnisic.ParkSmart.service.UserService;
import com.lazarnisic.ParkSmart.service.data.FeaturesData;
import com.lazarnisic.ParkSmart.service.data.ParkingSpotSaleData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ParkingSpotSaleServiceImpl implements ParkingSpotSaleService {

    private final UserMapper userMapper;
    private final UserService userService;
    private final ParkingSpotSaleMapper parkingSpotSaleMapper;
    private final ParkingSpotSaleRepository parkingSpotSaleRepository;
    private final CityService cityService;
    private final ParkingDetailsService parkingDetailsService;

    @Override
    public ParkingSpotSaleDTO create(ParkingSpotSaleData parkingSpotSaleData) {
        ParkingSpotSale parkingSpotSale = new ParkingSpotSale();
        parkingSpotSale.setOwner(userMapper.toEntity(userService.getAuthenticatedUser()));
        City city = cityService.findOrCreate(parkingSpotSaleData.getCity(), parkingSpotSaleData.getCountry());
        parkingSpotSale.setCity(city);
        parkingSpotSale.setAddress(parkingSpotSaleData.getAddress());
        parkingSpotSale.setPrice(parkingSpotSaleData.getPrice());
        parkingSpotSale.setTimestamp(LocalDateTime.now());
        return parkingSpotSaleMapper.toDto(parkingSpotSaleRepository.save(parkingSpotSale));
    }

    @Override
    public List<ParkingSpotSaleDTO> getSaleParkingSpotsForCity(String cityName) {
        List<ParkingSpotSale> parkingSpots = parkingSpotSaleRepository.findAllByCity_Name(cityName);
        return parkingSpotSaleMapper.toDto(parkingSpots);
    }

    @Override
    public ParkingSpotSaleDTO createParkingFeatures(Long parkingSpotId, FeaturesData featuresData) {
        ParkingSpotSale parkingSpotsale = parkingSpotSaleRepository.findById(parkingSpotId)
                .orElseThrow(() -> new ParkingSpotNotFound(parkingSpotId));

        Features features = parkingDetailsService.createFeatures(featuresData);

        parkingSpotsale.setFeatures(features);
        return parkingSpotSaleMapper.toDto(parkingSpotSaleRepository.save(parkingSpotsale));
    }
}
