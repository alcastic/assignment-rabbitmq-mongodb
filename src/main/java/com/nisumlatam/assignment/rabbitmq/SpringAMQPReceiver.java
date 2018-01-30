package com.nisumlatam.assignment.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SpringAMQPReceiver implements IReceiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringAMQPReceiver.class);

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Override
    public String consumeMessage(Queue queue) {
        String message = (String) rabbitTemplate.receiveAndConvert(queue.getName());
        LOGGER.info("Received '" + message + "'");
        return message;
    }
}