package com.sy.rockermq.client.one.service;

import com.sy.rockermq.client.one.rocketmq.StreamClient;
import com.sy.rocketmq.common.domain.ComMessage;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @Author: shenyao
 * @Date: Created by 2020/12/6 22:14
 * @description:
 */

@Component
public class ReceiveService {

    @StreamListener("shuru")
    public void receiveInput(@Payload ComMessage message) {
        System.out.println("收到消息: " + message);
    }



}
