package com.nisumlatam.assignment.rabbitmq;

import com.rabbitmq.client.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Component
public class MessageConsumer implements IConsumer {

    @Value("${rabbitmq.host}")
    private String RABBIT_MQ_HOST;

    @Value("${rabbitmq.port}")
    private int RABBIT_MQ_PORT;

    @Value("${rabbitmq.queue}")
    private String RABBIT_MQ_QUEUE;

    @Override
    public String consumeMessage() throws Exception {
        Connection connection = obtainConnection();
        Channel channel = obtainChannel(connection);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" [x] Received '" + message + "'");
            }
        };
        String message = channel.basicConsume(RABBIT_MQ_QUEUE, true, consumer);

        channel.close();
        connection.close();

        return message;
    }

    private Channel obtainChannel(Connection connection) throws IOException {
        Channel channel = connection.createChannel();
        channel.queueDeclare(RABBIT_MQ_QUEUE, false, false, false, null);
        return channel;
    }

    private Connection obtainConnection() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(RABBIT_MQ_HOST);
        factory.setPort(RABBIT_MQ_PORT);
        return factory.newConnection();
    }
}
