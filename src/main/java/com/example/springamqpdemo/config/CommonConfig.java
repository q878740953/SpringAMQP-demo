package com.example.springamqpdemo.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CommonConfig implements BeanPostProcessor {
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof RabbitTemplate){
            RabbitTemplate rabbitTemplate = (RabbitTemplate) bean;
            rabbitTemplate.setReturnsCallback(returnedMessage -> {
                // 记录日志
                log.error("消息发送到队列失败，响应码：{}，失败原因：{}，交换机：{}，路由key：{}，消息：{}",
                        returnedMessage.getReplyCode(), returnedMessage.getReplyText(), returnedMessage.getExchange(), returnedMessage.getRoutingKey(), returnedMessage.getMessage());
            });
            return rabbitTemplate;
        }
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
}
