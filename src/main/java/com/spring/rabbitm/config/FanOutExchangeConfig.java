package com.spring.rabbitm.config;

import jakarta.annotation.PostConstruct;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class FanOutExchangeConfig {

    @Autowired
    private AmqpAdmin amqpAdmin;

    @Value("${rabbit.fanout1.queue}")
    private String fanout1Queue1;

    @Value("${rabbit.fanout2.queue}")
    private String fanout1Queue2;

    @Value("${rabbit.fanout3.queue}")
    private String fanout1Queue3;

    @Value("${rabbit.fanout.exchange}")
    private String exchange;

    @Bean
    Queue createFanoutQueue1(){
        return new Queue(fanout1Queue1,true,false,false);
    }
    @Bean
    Queue createFanoutQueue2(){
        return new Queue(fanout1Queue2,true,false,false);
    }
    @Bean
    Queue createFanoutQueue3(){
        return new Queue(fanout1Queue3,true,false,false);
    }

    @Bean
    FanoutExchange createFanoutExchange(){
        return new FanoutExchange(exchange,true,false);
    }
    @Bean
    public AmqpTemplate fanoutQueue(ConnectionFactory connectionFactory, MessageConverter messageConverter){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        rabbitTemplate.setExchange(exchange);
        return rabbitTemplate;
    }
    @PostConstruct
    public void init(){
        amqpAdmin.declareQueue(createFanoutQueue1());
        amqpAdmin.declareQueue(createFanoutQueue2());
        amqpAdmin.declareQueue(createFanoutQueue3());

        amqpAdmin.declareExchange(createFanoutExchange());

    }


}
