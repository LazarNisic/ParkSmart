package com.lazarnisic.ParkSmart.service.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationData {
    private Long ParkingSpotId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
