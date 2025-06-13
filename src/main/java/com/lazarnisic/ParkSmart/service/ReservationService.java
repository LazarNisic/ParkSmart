package com.lazarnisic.ParkSmart.service;

import com.lazarnisic.ParkSmart.dto.ReservationDTO;
import com.lazarnisic.ParkSmart.service.data.MonthlyReservationData;
import com.lazarnisic.ParkSmart.service.data.ReservationData;

public interface ReservationService {
    ReservationDTO createReservation(ReservationData reservationData);
    ReservationDTO createMonthlyReservation(MonthlyReservationData monthlyReservationData);
}
