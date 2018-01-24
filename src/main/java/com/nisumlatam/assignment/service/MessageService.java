package com.nisumlatam.assignment.service;

import com.nisumlatam.assignment.domain.Message;
import com.nisumlatam.assignment.domain.StatusMessage;
import com.nisumlatam.assignment.rabbitmq.IPublisher;
import com.nisumlatam.assignment.repository.MessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageService.class);
    @Autowired
    MessageRepository messageRepository;

    @Autowired
    IPublisher publisher;

    public void distributeMessage(String message) {
        Message msg = null;
        LOGGER.debug("publishing message into RabbitMQ");
        try {
            publisher.publishMessage("Be Original!");
            msg = new Message(message, StatusMessage.PUBLISHED);
        } catch (Exception e) {
            LOGGER.debug("Error publishing message in RabbitMQ");
            msg = new Message(message, StatusMessage.MISSED);
        }
        LOGGER.debug("saving message into mongodb");
        messageRepository.save(msg);
    }
}
