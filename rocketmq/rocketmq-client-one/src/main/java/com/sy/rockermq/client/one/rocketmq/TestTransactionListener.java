package com.sy.rockermq.client.one.rocketmq;

import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.messaging.Message;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: shenyao
 * @Date: Created by 2020/12/15 21:20
 * @description: 测试事务消息
 */

@RocketMQTransactionListener
public class TestTransactionListener implements RocketMQLocalTransactionListener {

    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object o) {
        System.out.println("执行本地事务----");

        System.out.println(System.currentTimeMillis());
        System.out.println(df.format(new Date()));
        return RocketMQLocalTransactionState.UNKNOWN;
      /*  }else if (xxx){
            return RocketMQLocalTransactionState.COMMIT;
        }
        return RocketMQLocalTransactionState.ROLLBACK;
*/
    }

    /**
     * 本地事务的检查，检查本地事务是否成功
     */
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
        System.out.println("检查事务----");
        System.out.println(System.currentTimeMillis());
        System.out.println(df.format(new Date()));
        return RocketMQLocalTransactionState.UNKNOWN;
        //if (xx){
        //    System.out.println("还需要再检查");
        //    return RocketMQLocalTransactionState.UNKNOWN;
        //}else if (xxx){
        //    System.out.println("已确定");
        //    return RocketMQLocalTransactionState.COMMIT;
        //}
        //System.out.println("消息废弃");
        //return RocketMQLocalTransactionState.ROLLBACK;

    }
}
