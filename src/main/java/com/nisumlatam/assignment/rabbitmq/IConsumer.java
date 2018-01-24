package com.nisumlatam.assignment.rabbitmq;

public interface IConsumer {
    String consumeMessage() throws Exception;
}
