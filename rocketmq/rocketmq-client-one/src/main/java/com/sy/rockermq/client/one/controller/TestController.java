package com.sy.rockermq.client.one.controller;

import com.sy.rockermq.client.one.rocketmq.AscySendCallback;
import com.sy.rocketmq.common.domain.ComMessage;
import com.sy.rocketmq.common.utils.SnowFlakeUtil;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.MessageConst;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Author: shenyao
 * @Date: Created by 2020/12/3 21:33
 * @description:
 */
@RestController
public class TestController {


    @Resource
    private RocketMQTemplate rocketMQTemplate;

    @GetMapping("/test")
    public void sdf() {
        SendResult orderInfo = rocketMQTemplate.syncSend("xiaoxi", ComMessage.builder().messageId(123L).content("测试消息").build());
        System.out.println(orderInfo);
    }

    @GetMapping("/test2")
    public void asdf() {
        ComMessage msg = ComMessage.builder().content("异步消息").build();
        rocketMQTemplate.asyncSend("xiaoxi",msg,new AscySendCallback(msg));
    }

    @GetMapping("/test3")
    public void asdf3() {

    }


    @GetMapping("/test4")
    public void sadf() {

    }


}
