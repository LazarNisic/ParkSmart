package com.lazarnisic.ParkSmart.mapper;

import com.lazarnisic.ParkSmart.dto.CityDTO;
import com.lazarnisic.ParkSmart.model.City;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CityMapper extends EntityMapper<CityDTO, City>{
}
