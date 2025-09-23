package com.lazarnisic.ParkSmart.mapper;

import com.lazarnisic.ParkSmart.dto.ReviewDTO;
import com.lazarnisic.ParkSmart.model.ParkingSpotRent;
import com.lazarnisic.ParkSmart.model.Review;
import com.lazarnisic.ParkSmart.model.User;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    @Named("toDto")
    @Mapping(source = "parkingSpotRent.id", target = "parkingSpotRent_Id")
    @Mapping(source = "user.id", target = "user_Id")
    ReviewDTO toDto(Review review);

    @Named("toEntity")
    @Mapping(source = "parkingSpotRent_Id", target = "parkingSpotRent")
    @Mapping(source = "user_Id", target = "user")
    Review toEntity(ReviewDTO dto);

    @IterableMapping(qualifiedByName = "toDto")
    List<ReviewDTO> toDtoList(List<Review> reviews);

    @IterableMapping(qualifiedByName = "toEntity")
    List<Review> toEntityList(List<ReviewDTO> dtos);

    default ParkingSpotRent mapParkingSpotRent(Long id) {
        if (id == null) return null;
        ParkingSpotRent spot = new ParkingSpotRent();
        spot.setId(id);
        return spot;
    }

    default Long mapParkingSpotRent(ParkingSpotRent spot) {
        return spot != null ? spot.getId() : null;
    }

    default User mapUser(Long id) {
        if (id == null) return null;
        User user = new User();
        user.setId(id);
        return user;
    }

    default Long mapUser(User user) {
        return user != null ? user.getId() : null;
    }
}
