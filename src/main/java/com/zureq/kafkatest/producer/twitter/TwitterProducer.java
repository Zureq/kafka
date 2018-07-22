package com.zureq.kafkatest.producer.twitter;

import com.google.gson.Gson;
import com.twitter.hbc.core.Client;
import com.zureq.kafkatest.kafka.KafkaService;
import com.zureq.kafkatest.model.twitter.TwitterMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.util.concurrent.BlockingQueue;

@Service
@EnableAsync
public class TwitterProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(TwitterProducer.class);

    @Resource
    private BlockingQueue<String> blockingQueue;

    @Autowired
    private Client twitterClient;

    @Autowired
    private KafkaService kafkaService;

    /**
     * This method is the real consumer of the Twitter stream.
     * It connects to Twitter and waits for the incoming messages from Twitter.
     * Once it receives a Twitter message, it parses the message
     * and forwards the message text to the Kafka service.
     *
     * @throws InterruptedException if interrupted while waiting for the incoming Twitter message
     */
    @Async
    public void run() throws InterruptedException {
        LOGGER.info("Twitter data feed consumption started");

        twitterClient.connect();

        Gson gson = new Gson();

        while (!twitterClient.isDone()) {
            String jsonMessage = blockingQueue.take();
            LOGGER.debug("Received json msg: {}", jsonMessage);

            TwitterMessage twitterMessage = gson.fromJson(jsonMessage, TwitterMessage.class);
            LOGGER.debug("Parsed twitter msg: {}", twitterMessage);

            kafkaService.send(twitterMessage.getText());
        }
    }

    @PreDestroy
    protected void destroy() {
        twitterClient.stop();
    }
}
