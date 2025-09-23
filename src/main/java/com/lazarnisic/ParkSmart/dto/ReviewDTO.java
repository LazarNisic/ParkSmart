package com.lazarnisic.ParkSmart.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class ReviewDTO {
    private Long id;
    private Long parkingSpotRent_Id;
    private Long user_Id;
    private int rating;
    private String comment;
    private LocalDateTime timestamp;
}
