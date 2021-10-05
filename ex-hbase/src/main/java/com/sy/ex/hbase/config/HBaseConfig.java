package com.sy.ex.hbase.config;

import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: sy
 * @Date: Created by 2021/9/29 17:39
 * @description:
 */
@Configuration
public class HBaseConfig {

    @Bean
    public HBaseService getHbaseService() {
        org.apache.hadoop.conf.Configuration conf = HBaseConfiguration.create();
        conf.set(HConstants.ZOOKEEPER_QUORUM, "shenyao:2181");
        //conf.set("hbase.client.scanner.timeout.period", "9999");
        return new HBaseService(conf);
    }





}
