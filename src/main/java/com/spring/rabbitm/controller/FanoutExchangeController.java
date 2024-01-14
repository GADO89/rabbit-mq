package com.spring.rabbitm.controller;

import com.spring.rabbitm.model.MessageDto;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
        MessageDto message=new MessageDto("direct", LocalDateTime.now());
        fanoutQueue.convertAndSend(message);

        return "Success Fanout";
    }

}
