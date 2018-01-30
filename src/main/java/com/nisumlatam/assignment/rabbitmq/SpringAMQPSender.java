package com.nisumlatam.assignment.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SpringAMQPSender implements ISender {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringAMQPSender.class);

    @Autowired
    private RabbitTemplate template;

    @Override
    public void publishMessage(Queue queue, String message) {
        this.template.convertAndSend(queue.getName(), message);
        LOGGER.info("Sent '" + message + "'");
    }
}