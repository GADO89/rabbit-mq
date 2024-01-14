package com.spring.rabbitm.controller;

import com.spring.rabbitm.model.Message;
import com.spring.rabbitm.service.ConsumerService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ConsumerController {

    @Autowired
    private ConsumerService consumerService;


    @GetMapping("/messages/{queueName}")
    public  List<Message> getMessage(@PathVariable String queueName) {

        return consumerService.receiveMessages(queueName);
    }

}
