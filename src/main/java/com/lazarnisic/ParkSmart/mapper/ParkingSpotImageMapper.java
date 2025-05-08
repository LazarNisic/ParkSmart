package com.lazarnisic.ParkSmart.mapper;

import com.lazarnisic.ParkSmart.dto.ParkingSpotImageDTO;
import com.lazarnisic.ParkSmart.model.ParkingSpotImage;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ParkingSpotImageMapper extends EntityMapper<ParkingSpotImageDTO, ParkingSpotImage>{
}
