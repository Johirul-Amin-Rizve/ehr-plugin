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
public class KafkaTopic2ConsumerService {

    private final KafkaConsumerService kafkaConsumerService;

    @KafkaListener(topics = "topic2", groupId = "group2")
    public void consume(AppointmentRequest appointmentRequest) {
        KafkaConsumer kafkaConsumer = new KafkaConsumer();
        kafkaConsumer.setGroupName("Group-2");
        kafkaConsumer.setTopicName("Topic-2");
        // Convert AppointmentRequest to JSON String
        String jsonString = JsonUtil.convertToJsonString(appointmentRequest);
        kafkaConsumer.setObjectValue(jsonString);
        kafkaConsumerService.saveConsumer(kafkaConsumer);
    }
}
