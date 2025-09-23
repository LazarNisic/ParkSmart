package com.lazarnisic.ParkSmart.service.data;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewData {
    private Long ParkingSpotId;
    @Min(1)
    @Max(5)
    private int rating;
    private String comment;
}
