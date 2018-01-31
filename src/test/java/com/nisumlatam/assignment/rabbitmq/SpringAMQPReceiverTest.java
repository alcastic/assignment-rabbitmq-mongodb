package com.nisumlatam.assignment.rabbitmq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SpringAMQPReceiverTest {
    @Mock
    RabbitTemplate rabbitTemplate;

    @InjectMocks
    SpringAMQPReceiver springAMQPReceiver;

    @Test
    public void consumeMessage_shouldReturnAMessage() {
        // arrange
        String expected = "a_message";
        Queue queue = new Queue("queue");
        when(rabbitTemplate.receiveAndConvert(queue.getName())).thenReturn(expected);

        // act
        String result = springAMQPReceiver.consumeMessage(queue);

        // assert
        assertEquals(result, expected);
    }
}
