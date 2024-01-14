package com.spring.rabbitm.controller;

import com.spring.rabbitm.model.MessageDto;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/topic")
public class TopicExchangeController {

    @Autowired
    private AmqpTemplate topicQueue;

    @Value("${rabbit.direct1.bi}")
    private String binding1;

    @Value("${rabbit.direct2.bi}")
    private String binding2;

    @Value("${rabbit.direct3.bi}")
    private String binding3;

    @GetMapping("/message/{key}")
    public String sendMessage(@PathVariable String key) throws Exception {

        MessageDto message=new MessageDto("Topic", LocalDateTime.now());

        topicQueue.convertAndSend(key, message);
        return "Success Topic";
    }

}
