package com.lazarnisic.ParkSmart.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.lazarnisic.ParkSmart.enums.PaymentStatus;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class ReservationDTO {
    private Long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private double totalPrice;
    private PaymentStatus paymentStatus;
}
