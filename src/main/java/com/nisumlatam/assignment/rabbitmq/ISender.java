package com.nisumlatam.assignment.rabbitmq;

import org.springframework.amqp.core.Queue;

public interface ISender {

    void publishMessage(Queue queue, String message) throws Exception;

}
