package com.zureq.kafkatest.model.twitter;

import lombok.Data;

/**
 * This is just a sample Twitter message object. Only few properties are listed here.
 * @apiNote For full Twitter message specification go to https://dev.twitter.com/overview/api/tweets
 */
@Data
public class TwitterMessage {

    private String text;
    private String source;
    private Object geo;
    private Object place;
    private TwitterUser user;

}