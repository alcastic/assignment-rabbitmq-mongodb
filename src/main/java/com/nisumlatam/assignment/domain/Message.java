package com.nisumlatam.assignment.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Message {

    @Id
    private String id;
    private String message;

    public Message() {

    }

    public Message(String message) {
        this.message = message;
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
