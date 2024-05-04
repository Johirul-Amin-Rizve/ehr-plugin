package com.valhalla.ehrplugin.elation.controller;

import com.valhalla.ehrplugin.elation.service.impl.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class kafkaController {

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @PostMapping("/kafka")
    public void sendMessageToKafka(@RequestBody Object object) {
        kafkaProducerService.sendMessage(object);
    }
}

