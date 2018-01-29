package com.nisumlatam.assignment.rabbitmq;

public interface IPublisher {

    void publishMessage(String message) throws Exception;

}
