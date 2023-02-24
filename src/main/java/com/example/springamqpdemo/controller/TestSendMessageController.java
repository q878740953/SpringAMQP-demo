package com.example.springamqpdemo.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestSendMessageController {

    // 注入mq模板
    @Autowired
    private RabbitTemplate rabbitTemplate;

    private final String QUEUE_NAME = "simple.queue";

    @GetMapping("/simplequeue")
    public String testSendMessageSimpleQueue(String messages){
        for (int i = 0; i < 1000; i++) {
            // 发送消息
            rabbitTemplate.convertAndSend(QUEUE_NAME, messages + i);
        }
        return "ok";
    }
}
