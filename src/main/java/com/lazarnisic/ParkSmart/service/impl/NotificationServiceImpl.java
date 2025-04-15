package com.lazarnisic.ParkSmart.service.impl;

import com.lazarnisic.ParkSmart.service.NotificationService;
import com.lazarnisic.ParkSmart.service.kafka.KafkaProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final KafkaProducer kafkaProducer;
    private final JavaMailSender javaMailSender;

    @Override
    public void sendReservationNotification(String message) {
        log.info("Sending reservation notification: {}", message);
        kafkaProducer.sendMessage("reservation_notifications", message);
    }

    @Override
    public void sendEmailReservationNotification(String message) {
        log.info("Sending email notification: {}", message);
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo("lazarn9@gmail.com");
        mailMessage.setSubject("Reservation Notification");
        mailMessage.setText(message);
        javaMailSender.send(mailMessage);
        log.info("Email sent to user");
    }
}
