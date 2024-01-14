package com.spring.rabbitm.controller;

import com.spring.rabbitm.model.MessageDto;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/default-exchange")
public class DefaultExchangeController {

    @Autowired
   private AmqpTemplate defaultQueue;

    @GetMapping("/message")
    public void sendMessage(){
    MessageDto message=new MessageDto("default", LocalDateTime.now());
        defaultQueue.convertAndSend(message);
}
}
