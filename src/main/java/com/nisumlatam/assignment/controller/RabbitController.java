package com.nisumlatam.assignment.controller;

import com.nisumlatam.assignment.rabbitmq.IPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RabbitController {

    @Autowired
    IPublisher send;

    @Autowired
    IPublisher publisher;

    @GetMapping("spring")
    public ResponseEntity<Void> spring() {
        try {
            send.publishMessage("Be Original!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("pure")
    public ResponseEntity<Void> pure() {
        try {
            publisher.publishMessage("Be Original!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
