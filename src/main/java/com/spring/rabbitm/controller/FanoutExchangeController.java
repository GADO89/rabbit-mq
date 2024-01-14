package com.spring.rabbitm.controller;

import com.spring.rabbitm.model.Message;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/fanout")
public class FanoutExchangeController {

    @Autowired
    private AmqpTemplate fanoutQueue;


    @GetMapping("/message")
    public String sendMessage() throws Exception {
        Message message=new Message("direct", LocalDateTime.now());
        fanoutQueue.convertAndSend(message);

        return "Success Fanout";
    }

}
