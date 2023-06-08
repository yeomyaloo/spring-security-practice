package com.spring.security.practice.springsecuritypractice.config;

import org.springframework.beans.factory.annotation.Value;

public class RedisConfiguration {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.password}")
    private String password;
    @Value("${spring.redis.database}")
    private int database;

    private ClassLoader classLoader;


    
}
