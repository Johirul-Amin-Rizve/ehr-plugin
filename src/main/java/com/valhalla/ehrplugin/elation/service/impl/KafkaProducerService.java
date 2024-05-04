package com.valhalla.ehrplugin.elation.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private static final String TOPIC1 = "topic1";
    private static final String TOPIC2 = "topic2";

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessage(Object object) {
        kafkaTemplate.send(TOPIC1, object);
        kafkaTemplate.send(TOPIC2, object);
    }
}
