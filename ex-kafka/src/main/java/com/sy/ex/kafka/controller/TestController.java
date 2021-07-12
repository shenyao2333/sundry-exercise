package com.sy.ex.kafka.controller;

import com.sy.ex.kafka.producer.KafkaProducer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: sy
 * @Date: Created by 2021/7/9 15:38
 * @description:
 */
@RestController
@AllArgsConstructor
public class TestController {

    private final KafkaProducer kafkaProducer;

    @GetMapping("/test")
    public void test(String msg,String topic){
        kafkaProducer.send(msg,topic);
    }


}
