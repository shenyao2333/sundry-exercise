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
        //设置临时的hadoop环境变量，之后程序会去这个目录下的\bin目录下找winutils.exe工具，windows连接hadoop时会用到
        //System.setProperty("hadoop.home.dir", "D:\\Program Files\\Hadoop");
        //执行此步时，会去resources目录下找相应的配置文件，例如hbase-site.xml
        org.apache.hadoop.conf.Configuration conf = HBaseConfiguration.create();
        conf.set(HConstants.ZOOKEEPER_QUORUM, "101.200.155.94:2181");
        conf.set("hbase.rootdir","hdfs://101.200.155.94:9000/hbase");
        conf.set("fs.defaultFS","hdfs://101.200.155.941:9000/hbase");
        return new HBaseService(conf);
    }

}
