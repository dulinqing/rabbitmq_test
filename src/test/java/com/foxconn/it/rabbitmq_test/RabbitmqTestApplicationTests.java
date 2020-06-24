package com.foxconn.it.rabbitmq_test;

import com.foxconn.it.rabbitmq_test.controller.RabbitmqConfig;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RabbitmqTestApplicationTests {

    @Autowired
    RabbitTemplate rabbitTemplate;
    @Test
    void testSendByTopics() {

        for (int i = 0; i < 50; i++) {
            String massage="sms email infrom to user++++"+i;
            rabbitTemplate.convertAndSend(RabbitmqConfig.EXCHANGE_TOPICS_INFORM,"inform.sms.email",massage);
            System.out.println("Send Message is:'" + massage + "'");
        }
    }

}
