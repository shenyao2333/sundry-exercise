package com.sy.rocketmq.client.two.listener;

import com.sy.rocketmq.common.domain.ComMessage;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @Author: shenyao
 * @Date: Created by 2020/12/13 11:58
 * @description:
 */
@Component
@RocketMQMessageListener(topic = "xiaoxi", consumerGroup  = "shenyao",
        selectorExpression = "*" ,  messageModel = MessageModel.BROADCASTING)
public class ConsumerReply implements RocketMQListener<ComMessage>  {
    int i =  12;

    @Override
    public void onMessage(ComMessage comMessage) {
        i++;
        System.out.println(i);
        if (i<18){
            System.out.println("错误了");
            throw new RuntimeException("234");
        }
        System.out.println(comMessage);
        System.out.println("xiaoxi:test "+System.currentTimeMillis());
    }
}
