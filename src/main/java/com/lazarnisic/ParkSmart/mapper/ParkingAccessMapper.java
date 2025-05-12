package com.lazarnisic.ParkSmart.mapper;

import com.lazarnisic.ParkSmart.dto.ParkingAccessDTO;
import com.lazarnisic.ParkSmart.model.ParkingAccess;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ParkingAccessMapper extends EntityMapper<ParkingAccessDTO, ParkingAccess>{
}
