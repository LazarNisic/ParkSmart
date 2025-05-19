package com.lazarnisic.ParkSmart.mapper;

import com.lazarnisic.ParkSmart.dto.ParkingSpotRentDTO;
import com.lazarnisic.ParkSmart.model.ParkingSpotRent;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ParkingSpotRentMapper extends EntityMapper<ParkingSpotRentDTO, ParkingSpotRent>{
}
