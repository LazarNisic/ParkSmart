package com.lazarnisic.ParkSmart.service.impl;

import com.lazarnisic.ParkSmart.service.NotificationService;
import com.lazarnisic.ParkSmart.service.kafka.KafkaProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final KafkaProducer kafkaProducer;

    @Override
    public void sendReservationNotification(String message) {
        log.info("Sending reservation notification: {}", message);
        kafkaProducer.sendMessage("reservation_notifications", message);
    }

    @Override
    public void sendEmailReservationNotification(String message) {
        log.info(message);
        log.info("Email sent to user");
        //Add logic to send email
    }
}
