package com.sy.ex.kafka.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: sy
 * @Date: Created by 2021/7/13 10:22
 * @description:
 */
@Slf4j
@Configuration
@EnableKafka
public class KafkaConfig {

    /**
     *  修改分区数并不会导致数据的丢失，但是分区数只能增大不能减小
     *  创建一个分区为3，两个副本为2的topic， 副本的数量不能超过broker的数量，否则创建主题时会失败。
     * @return topic
     */
    @Bean
    public NewTopic updateTopic() {
        System.out.println("testTopic");
        return new NewTopic("testTopic",3, (short) 1);
    }


    /**
     * 操作kafka的admin工具
     * @return
     */
    @Bean
    public AdminClient kafkaAdmin() {
        log.info("进来这里--->");
        Map<String, Object> props = new HashMap<>();
        props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "123.56.97.130:9092");
        AdminClient adminClient = AdminClient.create( new KafkaAdmin(props).getConfig());
        return adminClient;
    }

}
