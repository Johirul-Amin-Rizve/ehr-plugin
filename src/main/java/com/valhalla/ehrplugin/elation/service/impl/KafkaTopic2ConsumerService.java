package com.valhalla.ehrplugin.elation.service.impl;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaTopic2ConsumerService {
    @KafkaListener(topics = "topic2", groupId = "group2")
    public void consume(Object object) {
        // Handle message received from topic2
    }
}
