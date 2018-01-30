package com.nisumlatam.assignment.rabbitmq;

import org.springframework.amqp.core.Queue;

public interface IReceiver {

    String consumeMessage(Queue queue) throws Exception;

}
