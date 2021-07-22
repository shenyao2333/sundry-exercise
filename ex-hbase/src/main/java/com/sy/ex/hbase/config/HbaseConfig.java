package com.sy.ex.hbase.config;


import com.google.common.base.Supplier;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HConstants;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.io.IOException;


@Configuration
public class HbaseConfig  {

    /**
     * 读取HBase的zookeeper地址
     */



    @Bean
    public org.apache.hadoop.conf.Configuration hbaseConfig() {
        org.apache.hadoop.conf.Configuration config = HBaseConfiguration.create();
        config.set(HConstants.ZOOKEEPER_QUORUM, "101.200.155.94:2181");
        return config;
    }
    //每次调用get方法就会创建一个Connection
    @Bean
    public Supplier<Connection> hbaseConnSupplier() {
        return () -> {

            try {
                return hbaseConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        };
    }

    @Bean
    //@Scope标明模式,默认单例模式.  prototype多例模式
    //若是在其他类中直接@Autowired引入的,多例就无效了,因为那个类在初始化的时候,已经创建了创建了这个bean了,之后调用的时候,不会重新创建,若是想要实现多例,就要每次调用的时候,手动获取bean
    // @Scope(value = "prototype")
    @Scope(value = "prototype")
    public Connection hbaseConnection() throws IOException {
        return ConnectionFactory.createConnection(hbaseConfig());
    }
}
