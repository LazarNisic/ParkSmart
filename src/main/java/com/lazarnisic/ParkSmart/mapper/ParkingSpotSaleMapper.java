package com.lazarnisic.ParkSmart.mapper;

import com.lazarnisic.ParkSmart.dto.ParkingSpotSaleDTO;
import com.lazarnisic.ParkSmart.model.ParkingSpotSale;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ParkingSpotSaleMapper extends EntityMapper<ParkingSpotSaleDTO, ParkingSpotSale>{
}
