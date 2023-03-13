package com.suif.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public final static String FIND_EXCHANGE = "find_exchange";
    public final static String FIND_QUEUE = "find_queue";
    public final static String ROUNTING_KEY = "find";

    @Bean
    DirectExchange exchange(){
        return new DirectExchange(FIND_EXCHANGE);
    }

    @Bean
    Queue queue(){
        return new Queue(FIND_QUEUE);
    }

    @Bean
    Binding binding(Queue queue,DirectExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(ROUNTING_KEY);
    }
}
