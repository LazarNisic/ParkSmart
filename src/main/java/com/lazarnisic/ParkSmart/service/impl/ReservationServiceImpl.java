package com.lazarnisic.ParkSmart.service.impl;

import com.lazarnisic.ParkSmart.dto.ReservationDTO;
import com.lazarnisic.ParkSmart.dto.UserDTO;
import com.lazarnisic.ParkSmart.enums.PaymentStatus;
import com.lazarnisic.ParkSmart.exception.ParkingSpotNotAvailable;
import com.lazarnisic.ParkSmart.exception.ParkingSpotNotFound;
import com.lazarnisic.ParkSmart.mapper.ReservationMapper;
import com.lazarnisic.ParkSmart.mapper.UserMapper;
import com.lazarnisic.ParkSmart.model.ParkingSpotRent;
import com.lazarnisic.ParkSmart.model.Reservation;
import com.lazarnisic.ParkSmart.repository.ParkingSpotRentRepository;
import com.lazarnisic.ParkSmart.repository.ReservationRepository;
import com.lazarnisic.ParkSmart.service.NotificationService;
import com.lazarnisic.ParkSmart.service.ReservationService;
import com.lazarnisic.ParkSmart.service.UserService;
import com.lazarnisic.ParkSmart.service.data.ReservationData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReservationServiceImpl implements ReservationService {

    private final ParkingSpotRentRepository parkingSpotRentRepository;
    private final ReservationRepository reservationRepository;
    private final UserMapper userMapper;
    private final UserService userService;
    private final ReservationMapper reservationMapper;
    private final NotificationService notificationService;

    @Override
    public ReservationDTO createReservation(ReservationData reservationData) {
        if (reservationData.getStartTime().isAfter(reservationData.getEndTime())) {
            throw new IllegalArgumentException("End time must be after start time");
        }

        ParkingSpotRent parkingSpotRent = parkingSpotRentRepository.findById(reservationData.getParkingSpotId()).
                orElseThrow(() -> new ParkingSpotNotFound(reservationData.getParkingSpotId()));

        if (!parkingSpotRent.isAvailable()) {
            throw new ParkingSpotNotAvailable(reservationData.getParkingSpotId());
        }

        List<Reservation> conflicts = reservationRepository
                .findConflictingReservations(reservationData.getParkingSpotId(), reservationData.getStartTime(),
                        reservationData.getEndTime());

        if (!conflicts.isEmpty()) {
            throw new ParkingSpotNotAvailable("Parking spot is already reserved for ths period");
        }

        long hours = Duration.between(reservationData.getStartTime(), reservationData.getEndTime()).toHours();
        double totalPrice = hours * parkingSpotRent.getPricePerHour();
        UserDTO user = userService.getAuthenticatedUser();

        Reservation reservation = new Reservation();
        reservation.setUser(userMapper.toEntity(user));
        reservation.setParkingSpotRent(parkingSpotRent);
        reservation.setStartTime(reservationData.getStartTime());
        reservation.setEndTime(reservationData.getEndTime());
        reservation.setTotalPrice(totalPrice);
        reservation.setPaymentStatus(PaymentStatus.PENDING);
        reservation.setTimestamp(LocalDateTime.now());

        ReservationDTO createdReservation = reservationMapper.toDto(reservationRepository.save(reservation));

        String notificationMessage = createdReservation.toString();
        notificationService.sendReservationNotification(notificationMessage + "\n" +
                "Address: " + parkingSpotRent.getAddress() + "\n" + parkingSpotRent.getCity());

        return createdReservation;
    }
}
