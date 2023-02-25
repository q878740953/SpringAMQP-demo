package com.example.springamqpdemo.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 路由模型配置类 用于申明消息队列和交换机
 */
@Configuration
public class DirectConfig {

    /**
     * 申明一个交换机
     */
    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange("test.direct");
    }

    /**
     * 申明一个队列
     */
    @Bean
    public Queue directQueue1(){
        return new Queue("direct.queue1");
    }

    /**
     * 申明一个队列
     */
    @Bean
    public Queue directQueue2(){
        return new Queue("direct.queue2");
    }
}
