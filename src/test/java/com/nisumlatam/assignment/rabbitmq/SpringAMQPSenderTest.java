package com.nisumlatam.assignment.rabbitmq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SpringAMQPSenderTest {

    @Mock
    RabbitTemplate rabbitTemplate;

    @InjectMocks
    SpringAMQPSender springAMQPSender;

    @Test
    public void publishMessage() {
        // arrange
        Queue queue = new Queue("queue");
        String message = "a_message";

        // act
        springAMQPSender.publishMessage(queue, message);

        // assert
        verify(rabbitTemplate, only()).convertAndSend(anyString(), anyString());
    }
}
