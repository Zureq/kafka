package com.zureq.kafkatest.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

@Service
@EnableAsync
public class KafkaService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    /**
     * Writes a message to Kafka under the given topic.
     *
     * @param payload The message payload
     */
    @Async
    public void send(String topic, String payload) {
        kafkaTemplate.send(topic, payload);
    }
}
