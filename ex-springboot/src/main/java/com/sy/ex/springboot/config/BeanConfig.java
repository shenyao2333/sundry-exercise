package com.sy.ex.springboot.config;

import com.sy.ex.springboot.domain.DataSource;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;


/**
 * @Author: shenyao
 * @Date: Created by 2021/1/8 21:46
 * @description:
 */
@Configurable
@Component
public class BeanConfig  {

    @ConfigurationProperties(prefix = "spring.datasource.druid.read", ignoreInvalidFields = true)
    @Bean(name = "readDruidDataSource")
    @Primary
    public DataSource readDruidDataSource(){
        return new DataSource();
    }

    @ConfigurationProperties(value = "spring.datasource.druid.write" , ignoreInvalidFields = true)
    @Bean(name = "writeDruidDataSource")
    public DataSource writeDruidDataSource(){
        return new DataSource();
    }

}
