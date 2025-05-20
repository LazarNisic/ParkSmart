package com.lazarnisic.ParkSmart.repository;

import com.lazarnisic.ParkSmart.model.ParkingSpotSale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParkingSpotSaleRepository extends JpaRepository<ParkingSpotSale, Long> {

    List<ParkingSpotSale> findAllByCity_Name(String cityName);

}
