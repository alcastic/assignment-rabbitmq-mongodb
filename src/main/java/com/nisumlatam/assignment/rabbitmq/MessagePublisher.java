package com.nisumlatam.assignment.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Component
public class MessagePublisher implements IPublisher {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessagePublisher.class);

    @Value("${rabbitmq.host}")
    private String RABBIT_MQ_HOST;

    @Value("${rabbitmq.port}")
    private int RABBIT_MQ_PORT;

    @Value("${rabbitmq.queue}")
    private String RABBIT_MQ_QUEUE;

    @Override
    public void publishMessage(String message) throws IOException, TimeoutException {
        Connection connection = obtainConnection();
        Channel channel = obtainChannel(connection);

        channel.basicPublish("", RABBIT_MQ_QUEUE, null, message.getBytes());
        LOGGER.debug(" [x] Sent '" + message + "'");

        channel.close();
        connection.close();
    }

    private Channel obtainChannel(Connection connection) throws IOException {
        Channel channel = connection.createChannel();
        channel.queueDeclare(RABBIT_MQ_QUEUE, false, false, false, null);
        return channel;
    }

    private Connection obtainConnection() throws IOException, TimeoutException {
        LOGGER.debug("getting RabbitMQ connection");
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(RABBIT_MQ_HOST);
        factory.setPort(RABBIT_MQ_PORT);
        return factory.newConnection();
    }
}
