package com.nisumlatam.assignment.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringAMQPConf {

    @Bean
    public Queue hello() {
        return new Queue("hello");
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