package com.lazarnisic.ParkSmart.service.impl;

import com.lazarnisic.ParkSmart.dto.CityDTO;
import com.lazarnisic.ParkSmart.exception.CityNotFound;
import com.lazarnisic.ParkSmart.mapper.CityMapper;
import com.lazarnisic.ParkSmart.model.City;
import com.lazarnisic.ParkSmart.repository.CityRepository;
import com.lazarnisic.ParkSmart.service.CityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private final CityMapper cityMapper;


    @Override
    public Page<CityDTO> findAll(Pageable pageable) {
        return cityRepository.findAll(pageable).map(cityMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public CityDTO findById(Long id) {
        City city = cityRepository.findById(id).orElseThrow(() -> new CityNotFound(id));
        return cityMapper.toDto(city);
    }

    @Override
    @Transactional(readOnly = true)
    public CityDTO findByName(String name) {
        City city = cityRepository.findByName(name).orElseThrow(() -> new CityNotFound(name));
        return cityMapper.toDto(city);
    }

    @Override
    @Transactional
    public City findOrCreate(String name, String country) {
        return cityRepository.findByNameAndCountry(name, country)
                .orElseGet(() -> {
                    City newCity = new City();
                    newCity.setName(name);
                    newCity.setCountry(country);
                    newCity.setTimestamp(LocalDateTime.now());
                    return cityRepository.save(newCity);
                });
    }

}
