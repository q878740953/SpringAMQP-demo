package com.example.springamqpdemo.listener;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class SpringRabbitListener {

    @RabbitListener(queuesToDeclare = @Queue(value = "simple.queue")) // 声明式队列，mq中没有该队列时，自动创建
    public void ListenSimpleQueue(String msg) throws InterruptedException {
        System.out.println("消费者1接收到的消息：【" + msg + "】");
        Thread.sleep(20);
    }


    @RabbitListener(queuesToDeclare = @Queue(value = "simple.queue")) // 声明式队列，mq中没有该队列时，自动创建
    public void ListenSimpleQueue2(String msg) throws InterruptedException {
        System.err.println("消费者2接收到的消息：【" + msg + "】");
        Thread.sleep(200);
    }


    @RabbitListener(queues = "fanout.queue1") // 声明式队列，mq中没有该队列时，自动创建
    public void ListenFanoutQueue1(String msg) throws InterruptedException {
        System.out.println("Fanout消费者1接收到的消息：【" + msg + "】");
        Thread.sleep(200);
    }

    @RabbitListener(queues = "fanout.queue2") // 声明式队列，mq中没有该队列时，自动创建
    public void ListenFanoutQueue2(String msg) throws InterruptedException {
        System.err.println("Fanout消费者2接收到的消息：【" + msg + "】");
        Thread.sleep(200);
    }
}
