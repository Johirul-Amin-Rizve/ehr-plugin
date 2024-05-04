package com.valhalla.ehrplugin.elation.service.impl;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaTopic1ConsumerService {
    @KafkaListener(topics = "topic1", groupId = "group1")
    public void consume(Object object) {
        // Handle message received from topic1
    }
}
