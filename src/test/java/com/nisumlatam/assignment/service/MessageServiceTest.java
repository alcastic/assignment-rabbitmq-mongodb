package com.nisumlatam.assignment.service;

import com.nisumlatam.assignment.domain.Message;
import com.nisumlatam.assignment.rabbitmq.ISender;
import com.nisumlatam.assignment.repository.MessageRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.amqp.core.Queue;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MessageServiceTest {

    @InjectMocks
    MessageService messageService;

    @Mock
    MessageRepository messageRepository;

    @Mock
    ISender sender;

    @Mock
    Queue inQueue;

    @Test(expected = IllegalArgumentException.class)
    public void deleteByID_shouldThrowsIllegalArgumentException_whenNullId() {
        String aNull = null;
        when(messageRepository.findOne(aNull)).thenThrow(new IllegalArgumentException());
        messageService.deleteByID(aNull);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteByID_shouldThrowsIllegalArgumentException_whenIdNotMatchWithAnElement() {
        String noMatch = "123456";
        when(messageRepository.findOne(noMatch)).thenThrow(new IllegalArgumentException());
        messageService.deleteByID(noMatch);
    }

    @Test
    public void deleteByID_shouldReturnTheMessage_whenIdMatchWithAnElement() {
        String match = "123456";
        Message expected = new Message("message");
        expected.setId(match);
        when(messageRepository.findOne(match)).thenReturn(expected);
        Message result = messageService.deleteByID(match);

        assertEquals(result, expected);
        assertEquals(match, expected.getId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateMessage_shouldThrowsIllegalArgumentException_whenNullId() {
        String aNull = null;
        Message message = new Message("message");
        when(messageRepository.findOne(aNull)).thenThrow(new IllegalArgumentException());
        messageService.updateMessage(message);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateMessage_shouldThrowsIllegalArgumentException_whenIdNotMatchWithAnElement() {
        String noMatch = "nomatch";
        Message message = new Message("message");
        message.setId(noMatch);
        when(messageRepository.findOne(noMatch)).thenThrow(new IllegalArgumentException());
        messageService.updateMessage(message);
    }

    @Test
    public void updateMessage_shouldReturnTheMessage_whenIdMatchWithAnElement() {
        // arrange
        String match = "match";
        Message expected = new Message("message");
        expected.setId(match);
        when(messageRepository.findOne(match)).thenReturn(expected);
        when(messageRepository.save(expected)).thenReturn(expected);

        // act
        Message result = messageService.updateMessage(expected);

        // assert
        assertEquals(expected, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void loadMessageByID_shouldThrowsIllegalArgumentException_whenIdNotMatch() {
        String noMatch = "nomatch";
        when(messageRepository.findOne(noMatch)).thenThrow(new IllegalArgumentException());
        messageService.loadMessageByID(noMatch);
    }

    @Test
    public void loadMessageByID_shouldReturnTheMessage_whenIdMatch() {
        // arrange
        String match = "match";
        Message expected = new Message("message").setId(match);
        when(messageRepository.findOne(match)).thenReturn(expected);

        // act
        Message result = messageService.loadMessageByID(match);

        // assert
        assertEquals(result, expected);
    }

    @Test
    public void loadAllMessages_shouldReturnAnEmptyList_whenNoMessages() {
        // arrange
        when(messageRepository.findAll()).thenReturn(Collections.emptyList());

        // act
        List<Message> messages = messageService.loadAllMessages();

        // arrange
        assertSame(messages.size(), 0);
    }

    @Test
    public void loadAllMessages_shouldReturnNotEmptyList_whenTheAreMessages() {
        // arrange
        List<Message> mockMsgs = mock(List.class);
        when(mockMsgs.size()).thenReturn(10);
        when(messageRepository.findAll()).thenReturn(mockMsgs);

        // act
        List<Message> messages = messageService.loadAllMessages();

        // arrange
        assertNotEquals(messages.size(), 0);
    }

    @Test
    public void saveMessage_shouldReturnTheMessageProvided() {

        Message expected = new Message("message");
        when(messageRepository.insert(expected)).thenReturn(expected);

        // act
        Message result = messageService.saveMessage(expected);

        // assert
        assertEquals(result, expected);
    }

    @Test
    public void distributeMessage_shouldReturnAMessageObjectWithTheProvidedMessage() throws Exception {
        String expected = "a message";
        Message message = new Message(expected);
        when(messageRepository.save(any(Message.class))).thenReturn(message);

        // act
        String result = messageService.distributeMessage(expected).getMessage();

        // assert
        assertEquals(result, expected);
    }

    @Test(expected = RuntimeException.class)
    public void distributeMessage_shouldThrowsException_whenErrorPushingMessageToRabbitMQ() throws Exception {
        String message = "a message";
        doThrow(new RuntimeException()).when(sender).publishMessage(any(Queue.class), anyString());

        // act
        messageService.distributeMessage(message);
    }
}
