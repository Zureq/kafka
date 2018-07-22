package com.zureq.kafkatest.producer.twitter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * This component is a starting point of Twitter data feed consumption.
 */
@Component
public class Initializer {

    private static final Logger LOG = LoggerFactory.getLogger(Initializer.class);

    @Autowired
    private TwitterProducer twitterProducer;

    /**
     * This event listener is invoked on the Spring context initialization
     * which is basically the application startup.
     */
    @EventListener(ContextRefreshedEvent.class)
    public void handleContextRefresh() {
        LOG.info("Starting the Twitter producer : " + twitterProducer);

        try {
            twitterProducer.run();
        } catch (InterruptedException e) {
            LOG.warn("The twitterStreamConsumer.run() has been interrupted.", e);
        }
    }
}
