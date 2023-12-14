package com.spring.rabbitm.config;



import jakarta.annotation.PostConstruct;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DefaultExchangeConfig {

    @Value("${rabbit.default.queue}")
    private String defaultQueue;

    @Autowired
    private AmqpAdmin amqpAdmin;

    @Bean
    Queue createQueue(){
        return new Queue(defaultQueue,true,false,false);
    }
    @Bean
    Queue createQueue1(){
        return new Queue(defaultQueue+1,true,false,false);
    }

    @Bean
    public AmqpTemplate defaultQueue(ConnectionFactory connectionFactory, MessageConverter messageConverter){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        rabbitTemplate.setRoutingKey(defaultQueue);
        return rabbitTemplate;
    }

    @PostConstruct
    public void init(){
        amqpAdmin.declareQueue(createQueue());
        amqpAdmin.declareQueue(createQueue1());
    }
}