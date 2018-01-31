package com.nisumlatam.assignment.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
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
    public ConnectionFactory connectionFactory() {
        return new CachingConnectionFactory();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        return new RabbitTemplate(connectionFactory);
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