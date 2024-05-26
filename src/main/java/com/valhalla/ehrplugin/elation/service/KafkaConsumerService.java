package com.valhalla.ehrplugin.elation.service;

import com.valhalla.ehrplugin.elation.domain.KafkaConsumer;

public interface KafkaConsumerService {
    KafkaConsumer saveConsumer(KafkaConsumer kafkaConsumer);
}
