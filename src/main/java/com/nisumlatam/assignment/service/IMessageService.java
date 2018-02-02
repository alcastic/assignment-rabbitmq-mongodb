package com.nisumlatam.assignment.service;

import com.nisumlatam.assignment.domain.Message;

import java.util.List;

public interface IMessageService {

    Message distributeMessage(String message) throws Exception;

    Message saveMessage(Message message);

    List<Message> loadAllMessages();

    Message loadMessageByID(String id);

    Message updateMessage(Message message);

    Message deleteByID(String id);

}
