package com.nisumlatam.assignment.rabbitmq.pure;

import com.nisumlatam.assignment.rabbitmq.IPublisher;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Publisher implements IPublisher {

    @Value("${rabbitmq.host}")
    private String RABBIT_MQ_HOST;

    @Value("${rabbitmq.port}")
    private int RABBIT_MQ_PORT;

    @Value("${rabbitmq.queue}")
    private String RABBIT_MQ_QUEUE;

    @Override
    public void publishMessage(String message) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(RABBIT_MQ_HOST);
        factory.setPort(RABBIT_MQ_PORT);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(RABBIT_MQ_QUEUE, false, false, false, null);
        channel.basicPublish("", RABBIT_MQ_QUEUE, null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");
        channel.close();
        connection.close();
    }
}
