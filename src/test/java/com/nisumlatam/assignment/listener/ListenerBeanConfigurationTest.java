package com.nisumlatam.assignment.listener;

import com.nisumlatam.assignment.rabbitmq.IReceiver;
import com.nisumlatam.assignment.rabbitmq.ISender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ListenerBeanConfiguration.class},
        loader = AnnotationConfigContextLoader.class
)
public class ListenerBeanConfigurationTest {

    @Autowired
    MessageSaveMongoEventListener messageSaveMongoEventListener;

    @MockBean
    IReceiver receiver;

    @MockBean
    ISender sender;

    @MockBean
    Queue queue;

    @Test
    public void messageSaveMongoEventListenerBean_shouldNotBeNull() {
        assertNotNull(messageSaveMongoEventListener);
    }
}
