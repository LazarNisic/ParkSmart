package com.lazarnisic.ParkSmart.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.lazarnisic.ParkSmart.enums.PaymentStatus;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Data
@SuperBuilder
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class ReservationDTO {
    private Long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private double totalPrice;
    private PaymentStatus paymentStatus;

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy 'at' HH:mm");
        return "Reservation Details:\n" +
                "Start Time: " + startTime.format(formatter) + "\n" +
                "End Time: " + endTime.format(formatter) + "\n" +
                "Total Price(€): " + totalPrice + "\n" +
                "Payment Status: " + paymentStatus;
    }


}
