package com.sy.ex.springboot;

import com.sy.ex.springboot.config.ConfigTest;
import com.sy.ex.springboot.domain.DataSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * @Author: shenyao
 * @Date: Created by 2021/1/7 22:14
 * @description:
 */
@SpringBootTest
public class TestCoot {

    @Resource
    private ConfigTest configTest;

    @Resource
    private ApplicationContext applicationContext;


    @Test
    public void test1(){
        ArrayList<String> userName = configTest.getUserName();
        System.out.println(userName);
    }

    @Test
    public void test2(){
        DataSource readDruidDataSource =(DataSource) this.applicationContext.getBean("writeDruidDataSource");
        System.out.println(readDruidDataSource);
    }


}
