package com.zureq.kafkatest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.TimeUnit;

import com.zureq.kafkatest.consumer.Receiver;
import com.zureq.kafkatest.producer.Sender;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.rule.KafkaEmbedded;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringKafkaApplicationTest {

    private static String BOOT_TOPIC = "Topic1";

    @Autowired
    private Sender sender;

    @Autowired
    private Receiver receiver;

    @ClassRule
    public static KafkaEmbedded embeddedKafka = new KafkaEmbedded(1, true, BOOT_TOPIC);

    @Test
    public void testReceive() throws Exception {
        sender.send(BOOT_TOPIC, "Hello Boot!");

        receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
        assertThat(receiver.getLatch().getCount()).isEqualTo(0);
    }
}