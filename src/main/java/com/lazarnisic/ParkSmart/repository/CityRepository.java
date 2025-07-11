package com.lazarnisic.ParkSmart.repository;

import com.lazarnisic.ParkSmart.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Long> {
    Optional<City> findByName(String name);

    Optional<City> findByNameAndCountry(String name, String country);
}
