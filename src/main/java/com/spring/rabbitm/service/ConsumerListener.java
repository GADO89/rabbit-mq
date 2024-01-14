package com.spring.rabbitm.service;

import com.spring.rabbitm.model.Message;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class ConsumerListener {

    @RabbitListener(queues = {"${rabbit.direct1.queue}","${rabbit.direct3.queue}"},
            containerFactory = "simpleRabbitListenerContainerFactory")
    public void receiveMessages(Message message){
        System.out.println(message);
    }
}
