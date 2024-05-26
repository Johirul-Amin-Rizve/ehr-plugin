package com.valhalla.ehrplugin.elation.service.impl;

import com.valhalla.ehrplugin.elation.domain.KafkaConsumer;
import com.valhalla.ehrplugin.elation.dto.appointmentDto.AppointmentRequest;
import com.valhalla.ehrplugin.elation.essential.JsonUtil;
import com.valhalla.ehrplugin.elation.service.KafkaConsumerService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaTopic1ConsumerService {

    private final KafkaConsumerService kafkaConsumerService;

    @KafkaListener(topics = "topic1", groupId = "group1")
    public void consume(AppointmentRequest appointmentRequest) {
        KafkaConsumer kafkaConsumer = new KafkaConsumer();
        kafkaConsumer.setGroupName("Group-1");
        kafkaConsumer.setTopicName("Topic-1");
        // Convert AppointmentRequest to JSON String
        String jsonString = JsonUtil.convertToJsonString(appointmentRequest);
        kafkaConsumer.setObjectValue(jsonString);
        kafkaConsumerService.saveConsumer(kafkaConsumer);
    }
}
