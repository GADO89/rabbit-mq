package com.spring.rabbitm.controller;

import com.spring.rabbitm.model.Message;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/direct")
public class DirectExchangeController {

    @Autowired
    private AmqpTemplate directQueue;

    @Value("${rabbit.direct1.bi}")
    private String binding1;

    @Value("${rabbit.direct2.bi}")
    private String binding2;

    @Value("${rabbit.direct3.bi}")
    private String binding3;

    @GetMapping("/message/{num}")
    public String sendMessage(@PathVariable Integer num) throws Exception {
        String key;
        if (num==1){
            key=binding1;
        }
       else if (num==2){
            key=binding2;
        }
        else if (num==3){
            key=binding3;
        }
        else {
            throw new Exception("You musst enter 1,2 or 3 only");
        }
        Message message=new Message("direct", LocalDateTime.now());

        directQueue.convertAndSend(key, message);
        return "Success";
    }

}
