package com.nisumlatam.assignment.rabbitmq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = {SpringAMQPConf.class},
        loader = AnnotationConfigContextLoader.class
)
public class SpringAMQPConfTest {

    @Autowired
    Queue inQueue;

    @Autowired
    Queue outQueue;

    @Autowired
    ConnectionFactory connectionFactory;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    SpringAMQPReceiver receiver;

    @Autowired
    SpringAMQPSender sender;

    @Test
    public void inQueueBean_shouldNotBeNull() {
        assertNotNull(inQueue);
    }

    @Test
    public void outQueueBean_shouldNotBeNull() {
        assertNotNull(outQueue);
    }

    @Test
    public void connectionFactoryBean_shouldNotBeNull() {
        assertNotNull(connectionFactory);
    }

    @Test
    public void rabbitTemplateBean_shouldNotBeNull() {
        assertNotNull(rabbitTemplate);
    }

    @Test
    public void receiverBean_shouldNotBeNull() {
        assertNotNull(receiver);
    }

    @Test
    public void senderBean_shouldNotBeNull() {
        assertNotNull(sender);
    }
}
