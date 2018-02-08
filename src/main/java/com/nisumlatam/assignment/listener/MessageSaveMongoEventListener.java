package com.nisumlatam.assignment.listener;

import com.nisumlatam.assignment.domain.Message;
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
    private ISender sender;

    @Autowired
    private Queue outQueue;

    @Override
    public void onAfterSave(AfterSaveEvent<Message> event) {
        try {
            String message = event.getSource().getMessage();
            LOGGER.debug("Message picked: " + message);
            sender.publishMessage(outQueue, message);
        } catch (Exception e) {
            LOGGER.error("Error publishing into out-queue");
            throw new RuntimeException("Error publishing");
        }
    }
}