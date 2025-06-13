package com.lazarnisic.ParkSmart.service.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Month;
import java.time.YearMonth;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MonthlyReservationData {
    private Long parkingSpotId;
    private YearMonth startMonth;
    private YearMonth endMonth;
}
