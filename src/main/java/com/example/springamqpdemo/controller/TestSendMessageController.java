package com.example.springamqpdemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.SuccessCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/test")
@Slf4j
public class TestSendMessageController {

    // 注入mq模板
    @Autowired
    private RabbitTemplate rabbitTemplate;

    private final static String QUEUE_NAME = "simple.queue";

    @GetMapping("/simplequeue")
    public String testSendMessageSimpleQueue(String messages){
        for (int i = 0; i < 50; i++) {
            // 发送消息
            rabbitTemplate.convertAndSend(QUEUE_NAME, messages + i);
        }
        return "ok";
    }


    @GetMapping("/fanout")
    public String testSendMessageByFanout(String messages){
        // 申明交换机名称
        String fanoutExchange = "test.fanout";
        // 像交换机中 发送消息
        rabbitTemplate.convertAndSend(fanoutExchange, "", messages);
        return "ok";
    }


    @GetMapping("/direct")
    public String testSendMessageByDirect(String messages, String key){
        // 申明交换机名称
        String fanoutExchange = "test.direct";
        // 像交换机中 发送消息
        rabbitTemplate.convertAndSend(fanoutExchange, key, messages);
        return "ok";
    }

    @GetMapping("/messageAck")
    public String testSendMessageBymessageAck(String messages, String key){
        // 准备CorrelationData
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        correlationData.getFuture().addCallback(result -> {
            // 判断结果 是否成功投递到交换机
            if (result !=null && result.isAck()){
                // 成功投递到交换机
                log.info("消息投递到交换机成功,消息id：{}", correlationData.getId());
            }
            else {
                log.error("消息投递到交换机失败，消息id：{}", correlationData.getId());
            }
        }, ex -> {
            log.error("消息发送失败！", ex);
        });
        // 像交换机中 发送消息
        rabbitTemplate.convertAndSend("11amq.topic", key, messages, correlationData);
        return "ok";
    }
}
