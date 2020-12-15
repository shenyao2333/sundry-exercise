package com.sy.rockermq.client.one.rocketmq;

import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

/**
 * @Author: shenyao
 * @Date: Created by 2020/12/15 21:20
 * @description: 测试事务消息
 */

@RocketMQTransactionListener
public class TestTransactionListener implements RocketMQLocalTransactionListener {

    int i = 0 ;

    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object o) {

        System.out.println("执行本地事务----");
        MessageHeaders headers = message.getHeaders();
        //获取事务ID
        String transactionId = (String) headers.get(RocketMQHeaders.TRANSACTION_ID);
        Object payload = message.getPayload();
        System.out.println("transactionId is "+transactionId+",  消息为"+payload.toString());
        try{
            if (i==0){
                System.out.println("进来了");
                return RocketMQLocalTransactionState.UNKNOWN;
            }
            System.out.println("执行中");
            //执行成功，可以提交事务
            return RocketMQLocalTransactionState.ROLLBACK;
        }catch (Exception e){
            return RocketMQLocalTransactionState.ROLLBACK;
        }

    }

    /**
     * 本地事务的检查，检查本地事务是否成功
     */
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
        MessageHeaders headers = message.getHeaders();
        //获取事务ID
        String transactionId = (String) headers.get(RocketMQHeaders.TRANSACTION_ID);
        System.out.println("检查本地事务,事务ID: "+transactionId);
        //根据事务id从日志表检索
        i++;
        System.out.println(i);
        if(this.i >2){
            System.out.println("提交");
            return RocketMQLocalTransactionState.COMMIT;
        }  else  {
            System.out.println("在检查一下");
            return RocketMQLocalTransactionState.UNKNOWN;
        }

    }
}
