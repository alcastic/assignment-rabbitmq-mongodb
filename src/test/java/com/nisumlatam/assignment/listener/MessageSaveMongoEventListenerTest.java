package com.nisumlatam.assignment.listener;

import com.nisumlatam.assignment.domain.Message;
import com.nisumlatam.assignment.rabbitmq.IReceiver;
import com.nisumlatam.assignment.rabbitmq.ISender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.amqp.core.Queue;
import org.springframework.data.mongodb.core.mapping.event.AfterSaveEvent;

import java.io.IOException;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MessageSaveMongoEventListenerTest {

    @Mock
    private ISender sender;

    @Mock
    Queue outQueue;

    @InjectMocks
    MessageSaveMongoEventListener messageSaveMongoEventListener;

    @Test
    public void onAfterSave_shouldCallSenderPublishMessageMethod() throws Exception {
        AfterSaveEvent<Message> event = mock(AfterSaveEvent.class);
        Message message = new Message().setMessage("a_message");
        when(event.getSource()).thenReturn(message);
        messageSaveMongoEventListener.onAfterSave(event);
        verify(sender, only()).publishMessage(any(Queue.class), anyString());
    }

    @Test(expected = RuntimeException.class)
    public void onAfterSave_shouldNotThrowsException_whenInternalError() throws Exception {
        AfterSaveEvent<Message> event = mock(AfterSaveEvent.class);
        Message message = new Message().setMessage("a_message");
        when(event.getSource()).thenReturn(message);
        doThrow(new IOException()).when(sender).publishMessage(any(Queue.class), anyString());
        messageSaveMongoEventListener.onAfterSave(event);
    }

}
