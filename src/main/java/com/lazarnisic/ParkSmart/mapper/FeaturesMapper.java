package com.lazarnisic.ParkSmart.mapper;

import com.lazarnisic.ParkSmart.dto.FeaturesDTO;
import com.lazarnisic.ParkSmart.model.Features;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FeaturesMapper extends EntityMapper<FeaturesDTO, Features>{
}
