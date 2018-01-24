package com.nisumlatam.assignment.domain;

import org.springframework.data.annotation.Id;

public class Message {

    @Id
    private String id;
    private String message;
    private StatusMessage statusMessage;

    public Message() {

    }

    public Message(String message, StatusMessage statusMessage) {
        this.message = message;
        this.statusMessage = statusMessage;
    }

    public String getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public Message setId(String id) {
        this.id = id;
        return this;
    }

    public Message setMessage(String message) {
        this.message = message;
        return this;
    }
}
