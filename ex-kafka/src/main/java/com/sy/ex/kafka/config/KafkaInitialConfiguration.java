package com.sy.ex.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @Author: sy
 * @Date: Created by 2021/7/13 10:22
 * @description:
 */
@Component
public class KafkaInitialConfiguration {

    /**
     *  修改分区数并不会导致数据的丢失，但是分区数只能增大不能减小
     *  创建一个分区为3，两个副本为2的topic， 副本的数量不能超过broker的数量，否则创建主题时会失败。
     * @return topic
     */
    @Bean
    public NewTopic updateTopic() {
        return new NewTopic("testTopic",3, (short) 1);
    }

}
