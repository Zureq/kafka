package com.zureq.kafkatest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

//@SpringBootApplication
public class Application2 implements CommandLineRunner {

    public static Logger logger = LoggerFactory.getLogger(Application2.class);

    public static void main(String[] args) {
        SpringApplication.run(Application2.class, args).close();
    }

    @Autowired
    private KafkaTemplate<String, String> template;

    private final CountDownLatch latch = new CountDownLatch(3);

    @Override
    public void run(String... args) throws Exception {
        this.template.send("Topic1", "foo1");
        this.template.send("Topic1", "foo2");
        this.template.send("Topic1", "foo3");
        latch.await(60, TimeUnit.SECONDS);
        logger.info("All received");
    }

}
