package com.nisumlatam.assignment.listener;

import com.nisumlatam.assignment.domain.Message;
import com.nisumlatam.assignment.rabbitmq.IReceiver;
import com.nisumlatam.assignment.rabbitmq.ISender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterSaveEvent;

public class MessageSaveMongoEventListener extends AbstractMongoEventListener<Message> {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageSaveMongoEventListener.class);

    @Autowired
    private IReceiver receiver;

    @Autowired
    private ISender sender;

    @Autowired
    Queue inQueue;

    @Autowired
    Queue outQueue;

    @Override
    public void onAfterSave(AfterSaveEvent<Message> event) {
        try {
            String message = receiver.consumeMessage(inQueue);
            LOGGER.info("Message consumed: " + message);
            sender.publishMessage(outQueue, message);
        } catch (Exception e) {
            LOGGER.error("Error consuming service");
        }
    }
}