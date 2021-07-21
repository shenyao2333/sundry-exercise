package com.sy.ex.hbase.config;


import org.apache.hadoop.hbase.HBaseConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class HbaseConfig  {
    @Bean
    @ConditionalOnMissingBean(HbaseTemplate.class)
    public HbaseTemplate hbaseTemplate() {
        Configuration configuration = (Configuration) HBaseConfiguration.create();
        configuration.set(HBASE_QUORUM, this.hbaseProperties.getQuorum());

        String rootDir = this.hbaseProperties.getRootDir();
        if (StringUtils.isNotEmpty(rootDir)) {
            configuration.set(HBASE_ROOTDIR, rootDir);
        }

        String nodeParent = this.hbaseProperties.getNodeParent();
        if (StringUtils.isNotEmpty(nodeParent)) {
            configuration.set(HBASE_ZNODE_PARENT, nodeParent);
        }

        boolean prepareConnection = this.hbaseProperties.isPrepareConnection();

        HbaseTemplate hbaseTemplate = new HbaseTemplate(configuration);

        if (prepareConnection) {
            hbaseTemplate.initConnection();
        }
        return hbaseTemplate;
    }
}
