package com.lazarnisic.ParkSmart.service.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    public void sendMessage(String topic, String message) {
        log.info(String.format("Producing message: %s", message));
        this.kafkaTemplate.send(topic, message);
    }
}
