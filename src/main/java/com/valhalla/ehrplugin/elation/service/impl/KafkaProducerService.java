package com.valhalla.ehrplugin.elation.service.impl;

import com.valhalla.ehrplugin.elation.dto.appointmentDto.AppointmentRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private static final Logger logger = LoggerFactory.getLogger(KafkaProducerService.class);

    private static final String TOPIC1 = "topic1";
    private static final String TOPIC2 = "topic2";

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessage(AppointmentRequest appointmentRequest) {
        logger.info("Received request in kafka producer to sent appointment : {}", appointmentRequest);
        kafkaTemplate.send(TOPIC1, appointmentRequest);
        kafkaTemplate.send(TOPIC2, appointmentRequest);
    }
}
