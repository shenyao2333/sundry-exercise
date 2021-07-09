package com.sy.ex.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @Author: sy
 * @Date: Created by 2021/7/9 15:34
 * @description:
 */
@Slf4j
@Component
public class KafkaConsumer {


    @KafkaListener(topics = "testTopic", groupId = "testGroup")
    public void testTopic(ConsumerRecord<?, ?> record, Acknowledgment ack, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        System.out.println("testGroup  ,  top: ->"+topic);
        System.out.println(record.value());
    }

    @KafkaListener(topics = "testTopic2", groupId = "testGroup2")
    public void testTopic2(ConsumerRecord<?, ?> record, Acknowledgment ack, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        System.out.println("testGroup2  ,  top: ->"+topic);

        System.out.println(record.value());
        ack.acknowledge();

    }


}
