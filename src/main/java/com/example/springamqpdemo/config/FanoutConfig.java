package com.example.springamqpdemo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 扇出模型配置类 用于申明消息队列和交换机 以及他们之间的绑定关系
 */
@Configuration
public class FanoutConfig {

    /**
     * 申明一个交换机
     */
    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange("test.fanout");
    }

    /**
     * 申明一个队列
     */
    @Bean
    public Queue fanoutQueue1(){
        return new Queue("fanout.queue1");
    }

    /**
     * 申明一个队列
     */
    @Bean
    public Queue fanoutQueue2(){
        return new Queue("fanout.queue2");
    }

    /**
     * 绑定队列到交换机上
     */
    @Bean
    public Binding fanoutBindingQueue1(Queue fanoutQueue1, FanoutExchange fanoutExchange){
        return BindingBuilder
                .bind(fanoutQueue1)
                .to(fanoutExchange);
    }

    /**
     * 绑定队列到交换机上
     */
    @Bean
    public Binding fanoutBindingQueue2(Queue fanoutQueue2, FanoutExchange fanoutExchange){
        return BindingBuilder
                .bind(fanoutQueue2)
                .to(fanoutExchange);
    }
}
