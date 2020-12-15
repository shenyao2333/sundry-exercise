package com.sy.rocketmq.client.two.listener;

/**
 * @Author: shenyao
 * @Date: Created by 2020/12/14 22:07
 * @description:
 */
import com.sy.rocketmq.common.domain.ComMessage;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Service
@RocketMQMessageListener(consumerGroup = "shenyao", topic = "testTran")
//@RocketMQTransactionListener(maximumPoolSize  = 3 )
public class TransactionListenerServcie implements RocketMQListener<ComMessage>, RocketMQPushConsumerLifecycleListener {

    int i = 0;

    @Override
    public void onMessage(ComMessage comMessage) {
        System.out.println("消息成功");
        System.out.println(i);
        i++;
    }

    @Override
    public void prepareStart(DefaultMQPushConsumer defaultMQPushConsumer) {
        System.out.println(defaultMQPushConsumer);
        System.out.println(i);
    }


}
