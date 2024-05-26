package com.valhalla.ehrplugin.elation.service.impl;

import com.valhalla.ehrplugin.elation.domain.KafkaConsumer;
import com.valhalla.ehrplugin.elation.repository.KafkaConsumerRepository;
import com.valhalla.ehrplugin.elation.service.KafkaConsumerService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaConsumerServiceImpl implements KafkaConsumerService {

    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerServiceImpl.class);

    private final KafkaConsumerRepository kafkaConsumerRepository;

    @Override
    public KafkaConsumer saveConsumer(KafkaConsumer kafkaConsumer) {
        logger.info("Received request to create kafka consumer: {}", kafkaConsumer);
        return kafkaConsumerRepository.save(kafkaConsumer);
    }
}
