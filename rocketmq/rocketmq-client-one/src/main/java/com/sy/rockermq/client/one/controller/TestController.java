package com.sy.rockermq.client.one.controller;

import com.sy.rockermq.client.one.rocketmq.StreamClient;
import com.sy.rocketmq.common.domain.ComMessage;
import com.sy.rocketmq.common.utils.SnowFlakeUtil;
import org.apache.rocketmq.common.message.MessageConst;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.xml.ws.handler.MessageContext;
import java.util.Date;

/**
 * @Author: shenyao
 * @Date: Created by 2020/12/3 21:33
 * @description:
 */
@RestController
public class TestController {

    @Resource
    private StreamClient streamClient;

    @GetMapping("/test")
    public void sdf(){
        streamClient.shuchu().send(MessageBuilder.withPayload("测试消息").build());
    }

    @GetMapping("/test2")
    public void asdf() {
        long l = SnowFlakeUtil.getFlowIdInstance().nextId();

        ComMessage msg = ComMessage.builder().content("消息").messageId(l).sendTime(new Date()).build();

        Message<ComMessage> build = MessageBuilder.withPayload(msg)
                .setHeader(MessageConst.PROPERTY_DELAY_TIME_LEVEL, "10")
                .build();

        streamClient.shuchu().send(build);
    }

}
