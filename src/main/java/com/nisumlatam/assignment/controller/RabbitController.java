package com.nisumlatam.assignment.controller;

import com.nisumlatam.assignment.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RabbitController {

    @Autowired
    MessageService messageService;


    @PostMapping("messages")
    public ResponseEntity<Void> postMessage(@RequestBody String message) throws Exception {
        messageService.distributeMessage(message);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public String errorHandler() {
        return "Internal Server Error";
    }
}
