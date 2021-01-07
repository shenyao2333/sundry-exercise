package com.sy.ex.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @Author: shenyao
 * @Date: Created by 2021/1/7 22:17
 * @description:
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class BootAplication {

    public static void main(String[] args) {
        SpringApplication.run(BootAplication.class, args);
    }
}
