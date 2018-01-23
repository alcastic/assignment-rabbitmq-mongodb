package com.nisumlatam.assignment.rabbitmq.spring;

import com.nisumlatam.assignment.rabbitmq.IPublisher;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Configuration
@Component
public class Send implements IPublisher {
    private final static String QUEUE_NAME = "hello";
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private Receiver receiver;
    @Autowired
    private ConfigurableApplicationContext context;

    @Override
    public void publishMessage(String message) throws Exception {
        System.out.println("Sending message...");
        rabbitTemplate.convertAndSend(RMQConfiguration.QUEUE_NAME, "Hello from RabbitMQ!");
        receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
        context.close();
    }
}
