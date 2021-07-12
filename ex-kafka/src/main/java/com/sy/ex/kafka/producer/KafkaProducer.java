package com.sy.ex.kafka.producer;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * @Author: sy
 * @Date: Created by 2021/7/9 15:37
 * @description:
 */
@Slf4j
@Service
@AllArgsConstructor
public class KafkaProducer {


    private KafkaTemplate<String, Object> kafkaTemplate;


    public void send(Object obj,String topic) {
        String obj2String = JSONObject.toJSONString(obj);
        log.info("准备发送消息为：{}， topic：{}", obj2String,topic);
        //发送消息
        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(topic, obj);
        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onFailure(Throwable throwable) {
                //发送失败的处理
                log.info(  " - 生产者 发送消息失败：" + throwable.getMessage());
            }
            @Override
            public void onSuccess(SendResult<String, Object> stringObjectSendResult) {
                //成功的处理
                log.info( " - 生产者 发送消息成功：" + stringObjectSendResult.toString());
            }
        });

    }



}
