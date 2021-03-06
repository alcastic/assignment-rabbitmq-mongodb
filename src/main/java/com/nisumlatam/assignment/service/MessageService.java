package com.nisumlatam.assignment.service;

import com.nisumlatam.assignment.domain.Message;
import com.nisumlatam.assignment.rabbitmq.ISender;
import com.nisumlatam.assignment.repository.MessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService implements IMessageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageService.class);

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    ISender sender;

    @Autowired
    Queue inQueue;

    @Override
    public Message distributeMessage(String message) throws Exception {
        Message msg = null;
        LOGGER.debug("publishing message into RabbitMQ");
        try {
            sender.publishMessage(inQueue, message);
            msg = messageRepository.save(new Message(message));
        } catch (Exception e) {
            LOGGER.debug("Error publishing message in RabbitMQ");
            throw e;
        }
        return msg;
    }

    @Override
    public Message saveMessage(Message message) {
        return messageRepository.insert(message);
    }

    @Override
    public List<Message> loadAllMessages() {
        return messageRepository.findAll();
    }

    @Override
    public Message loadMessageByID(String id) {
        return messageRepository.findOne(id);
    }

    @Override
    public Message updateMessage(Message message) {
        messageRepository.findOne(message.getId());
        return messageRepository.save(message);
    }

    @Override

    public Message deleteByID(String id) {
        Message one = messageRepository.findOne(id);
        messageRepository.delete(id);
        return one;
    }

}
