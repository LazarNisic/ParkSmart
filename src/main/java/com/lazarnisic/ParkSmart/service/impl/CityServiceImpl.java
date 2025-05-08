package com.lazarnisic.ParkSmart.service.impl;

import com.lazarnisic.ParkSmart.dto.CityDTO;
import com.lazarnisic.ParkSmart.exception.CityNotFound;
import com.lazarnisic.ParkSmart.mapper.CityMapper;
import com.lazarnisic.ParkSmart.model.City;
import com.lazarnisic.ParkSmart.repository.CityRepository;
import com.lazarnisic.ParkSmart.service.CityService;
import com.lazarnisic.ParkSmart.service.data.CityData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private final CityMapper cityMapper;

    @Override
    public List<CityDTO> findAll() {
        return cityMapper.toDto(cityRepository.findAll());
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
    public CityDTO create(CityData cityData) {
        City city = new City();
        city.setName(cityData.getName());
        city.setCountry(cityData.getCountry());
        city.setTimestamp(LocalDateTime.now());
        return cityMapper.toDto(cityRepository.save(city));
    }
}
