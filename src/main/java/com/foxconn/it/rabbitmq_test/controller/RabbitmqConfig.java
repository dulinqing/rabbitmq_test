package com.foxconn.it.rabbitmq_test.controller;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {

    public static final String QUEUE_INFORM_EMAIL = "queue_inform_email";
    public static final String QUEUE_INFORM_SMS = "queue_inform_sms";
    public static final String EXCHANGE_TOPICS_INFORM="exchange_topics_inform";

    @Bean
    public Exchange exchange_topics_inform(){
        return ExchangeBuilder.topicExchange(EXCHANGE_TOPICS_INFORM).durable(true).build();
    }

    @Bean
    public Queue queue_inform_sms(){
        return new Queue(QUEUE_INFORM_SMS);
    }

    @Bean
    public Queue queue_inform_email(){
        return new Queue(QUEUE_INFORM_EMAIL);
    }

    @Bean
    public Binding binding_queue_inform_sms(@Qualifier("queue_inform_sms") Queue queue,
                                            @Qualifier("exchange_topics_inform") Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("inform.#.sms.#").noargs();
    }


    @Bean
    public Binding binding_queue_inform_email(@Qualifier("queue_inform_email") Queue queue,
                                              @Qualifier("exchange_topics_inform") Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("inform.#.email.#").noargs();
    }



}
