package com.lazarnisic.ParkSmart.mapper;

import com.lazarnisic.ParkSmart.dto.ParkingSpotDTO;
import com.lazarnisic.ParkSmart.model.ParkingSpot;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ParkingSpotMapper extends EntityMapper<ParkingSpotDTO, ParkingSpot>{
}
