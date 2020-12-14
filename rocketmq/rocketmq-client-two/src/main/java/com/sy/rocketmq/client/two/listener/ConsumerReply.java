package com.sy.rocketmq.client.two.listener;

import com.sy.rocketmq.common.domain.ComMessage;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQReplyListener;
import org.springframework.stereotype.Component;

/**
 * @Author: shenyao
 * @Date: Created by 2020/12/13 11:58
 * @description:
 */
@Component
@RocketMQMessageListener(topic = "xiaoxi", consumerGroup  = "shenyao",
        selectorExpression = "test")
public class ConsumerReply implements RocketMQListener<ComMessage> {


    @Override
    public void onMessage(ComMessage comMessage) {
        System.out.println(comMessage);
        System.out.println("xiaoxi:test "+System.currentTimeMillis());
    }
}
