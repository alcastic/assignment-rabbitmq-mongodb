package com.nisumlatam.assignment.listener;

import com.nisumlatam.assignment.domain.Message;
import com.nisumlatam.assignment.rabbitmq.IConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterSaveEvent;

public class MessageSaveMongoEventListener extends AbstractMongoEventListener<Message> {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageSaveMongoEventListener.class);

    @Autowired
    private IConsumer messageConsumer;

    @Override
    public void onAfterSave(AfterSaveEvent<Message> event) {
        try {
            String message = messageConsumer.consumeMessage();
            LOGGER.info("Message consumed: " + message);
        } catch (Exception e) {
            LOGGER.error("Error consuming service");
        }
    }
}