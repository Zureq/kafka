package com.zureq.kafkatest.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;

@Service
@EnableAsync
public class KafkaService {

    @Autowired
    private KafkaProducer<String, String> kafkaProducer;

    @Autowired
    private String topic;

    /**
     * Writes a message to Kafka under the given topic.
     *
     * @param payload The message payload
     */
    @Async
    public void send(String payload) {
        kafkaProducer.send(new ProducerRecord<>(topic, payload));
    }

    @PreDestroy
    protected void destroy() {
        kafkaProducer.close();
    }

    @Override
    public String toString() {
        return "KafkaService{" +
                "producer=" + kafkaProducer +
                ", topic='" + topic + '\'' +
                '}';
    }
}
