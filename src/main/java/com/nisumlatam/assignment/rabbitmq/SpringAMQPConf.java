package com.nisumlatam.assignment.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringAMQPConf {

    @Bean
    public Queue inQueue() {
        return new Queue("in-queue");
    }

    @Bean
    public Queue outQueue() {
        return new Queue("out-queue");
    }

    @Bean
    public SpringAMQPReceiver receiver() {
        return new SpringAMQPReceiver();
    }

    @Bean
    public SpringAMQPSender sender() {
        return new SpringAMQPSender();
    }
}