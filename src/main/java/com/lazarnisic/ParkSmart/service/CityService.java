package com.lazarnisic.ParkSmart.service;

import com.lazarnisic.ParkSmart.dto.CityDTO;
import com.lazarnisic.ParkSmart.model.City;

import java.util.List;

public interface CityService {
    List<CityDTO> findAll();

    CityDTO findById(Long id);

    CityDTO findByName(String name);

    City findOrCreate(String name, String country);
}
