package com.sy.rocketmq.client.two.listener;

/**
 * @Author: shenyao
 * @Date: Created by 2020/12/14 22:07
 * @description:
 */

import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Service
@RocketMQTransactionListener(maximumPoolSize  = 3)
public class TransactionListenerServcie implements RocketMQLocalTransactionListener {
    int i =0;

    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object o) {
        System.out.println("方法一" +  i);
        return RocketMQLocalTransactionState.ROLLBACK;
    }

    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
        System.out.println("方法二"+  i);
        return RocketMQLocalTransactionState.ROLLBACK;
    }
}
