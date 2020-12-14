package com.sy.rockermq.client.one.rocketmq;

import com.sy.rocketmq.common.domain.ComMessage;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;

/**
 * @Author: shenyao
 * @Date: Created by 2020/12/13 12:35
 * @description:
 */
public class AscySendCallback implements SendCallback {

    private ComMessage comMessage;

    public AscySendCallback(ComMessage comMessage) {
        this.comMessage = comMessage;
    }

    @Override
    public void onSuccess(SendResult sendResult) {
        System.out.println("发送成功时间为："+System.currentTimeMillis());
        System.out.println("消息内容为: "+  this.comMessage);

    }

    @Override
    public void onException(Throwable throwable) {
        System.out.println("发送失败: "+ throwable);
    }
}
