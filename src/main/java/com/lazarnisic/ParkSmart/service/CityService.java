package com.lazarnisic.ParkSmart.service;

import com.lazarnisic.ParkSmart.dto.CityDTO;
import com.lazarnisic.ParkSmart.service.data.CityData;

import java.util.List;

public interface CityService {
    List<CityDTO> findAll();

    CityDTO findById(Long id);

    CityDTO findByName(String name);

    CityDTO create(CityData cityData);
}
