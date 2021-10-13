package com.sy.ex.hbase.config;

import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: sy
 * @Date: Created by 2021/10/13 11:11
 * @description:
 */
@Configuration
public class HbaseConfigs {

    /**
     * 配置HBase连接参数
     *
     * @return
     */
    @Bean
    public org.apache.hadoop.conf.Configuration hbaseConfig() {
        org.apache.hadoop.conf.Configuration config = HBaseConfiguration.create();
        config.set(HConstants.ZOOKEEPER_QUORUM, "shenyao:2181");
        return config;
    }


}
