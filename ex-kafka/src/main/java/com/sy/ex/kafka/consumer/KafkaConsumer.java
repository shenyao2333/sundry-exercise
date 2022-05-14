package com.sy.ex.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;


/**
 * @Author: sy
 * @Date: Created by 2021/7/9 15:34
 * @description:
 */
@Slf4j
@Component
public class KafkaConsumer {

    /**
     *
     * topics 可配置多个 topic
     */
    @KafkaListener(topics = "point_topic", groupId = "testGroup")
    public void testTopic(ConsumerRecord<?, ?> record, Acknowledgment ack, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        System.out.println("消费消息---》 testGroup  ,  top: ->"+topic);
        System.out.println(record.value());
        ack.acknowledge();
    }

}
