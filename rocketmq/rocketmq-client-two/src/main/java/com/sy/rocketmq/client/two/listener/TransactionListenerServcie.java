package com.sy.rocketmq.client.two.listener;

/**
 * @Author: shenyao
 * @Date: Created by 2020/12/14 22:07
 * @description:
 */
import com.sy.rocketmq.common.domain.ComMessage;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RocketMQMessageListener(consumerGroup = "shenyao", topic = "testTran" ,reconsumeTimes = 4)
//@RocketMQTransactionListener(maximumPoolSize  = 3 )
public class TransactionListenerServcie implements RocketMQListener<ComMessage> , MessageListenerConcurrently {

    int i = 0;

    @Override
    public void onMessage(ComMessage comMessage) {
        System.out.println("消息成功");
        System.out.println(i);
        i++;
    }


    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
        return null;
    }
}
