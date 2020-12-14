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

    /**
     * 发送同步消息
     */
    @GetMapping("/test1")
    public void test1() {
        SendResult orderInfo = rocketMQTemplate.syncSend("xiaoxi", ComMessage.builder().messageId(123L).content("测试消息").build());
        System.out.println(orderInfo);
    }


    /**
     * 发送异步消息，继承SendCallback接收返回数据
     */
    @GetMapping("/test2")
    public void test2() {
        ComMessage msg = builder("异步消息");
        rocketMQTemplate.asyncSend("xiaoxi",msg,new AscySendCallback(msg));
        System.out.println("时间为："+System.currentTimeMillis());
    }

    /**
     * 异步不等待确定
     */
    @GetMapping("/test3")
    public void test3() {
        rocketMQTemplate.sendOneWay("xiaoxi",builder("异步不等待消息，不关心发送结构"));
    }


    /**
     * 发送顺序消息，第三个参数放key作用是能利用hash计算将同一类的消息推送到同一个broke里
     */
    @GetMapping("/test4")
    public void test4() {
        rocketMQTemplate.syncSendOrderly("xiaoxi", builder("发送顺时消息"), "1");
    }


    private ComMessage builder(String content){
        return ComMessage.builder().messageId(System.currentTimeMillis()).content(content).build();


    }



}
