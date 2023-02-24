package com.example.springamqpdemo.listener;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class SpringRabbitListener {

    @RabbitListener(queuesToDeclare = @Queue(value = "simple.queue")) // 声明式队列，mq中没有该队列时，自动创建
    public void ListenSimpleQueue(String msg) throws InterruptedException {
//        Thread.sleep(1000);
        System.out.println(msg);
    }
}
