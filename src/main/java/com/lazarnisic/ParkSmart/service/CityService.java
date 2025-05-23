package com.lazarnisic.ParkSmart.service;

import com.lazarnisic.ParkSmart.dto.CityDTO;
import com.lazarnisic.ParkSmart.model.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface CityService {
    Page<CityDTO> findAll(Pageable pageable);

    CityDTO findById(Long id);

    CityDTO findByName(String name);

    City findOrCreate(String name, String country);
}
