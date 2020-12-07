package com.sy.rockermq.client.one.rocketmq;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;


/**
 * @Author: shenyao
 * @Date: Created by 2020/12/7 20:31
 * @description:
 */
public interface StreamClient {

    @Output("shuchu")
    SubscribableChannel shuchu();

    @Input("shuru")
    MessageChannel shuru();

}
