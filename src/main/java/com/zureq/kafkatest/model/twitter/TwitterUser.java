package com.zureq.kafkatest.model.twitter;

import lombok.Data;

/**
 * This is just a sample Twitter message object. Only few properties are listed here.
 * @apiNote For full Twitter message specification go to https://dev.twitter.com/overview/api/tweets
 */
@Data
public class TwitterUser {

    private String name;
    private String location;

}