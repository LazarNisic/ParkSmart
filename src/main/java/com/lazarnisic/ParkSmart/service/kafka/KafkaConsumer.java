package com.lazarnisic.ParkSmart.service.kafka;

import com.lazarnisic.ParkSmart.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumer {

    private final NotificationService notificationService;

    @KafkaListener(topics = "reservation_notifications", groupId = "group_id")
    public void consume(String message) {
        try {
            log.info(String.format("Consumed message: %s", message));
            notificationService.sendEmailReservationNotification(message);
        } catch (Exception e) {
            log.error("Error while processing Kafka message", e);
            // Opcionalno: možeš poslati poruku u dead-letter topic ručno
        }

    }
}
