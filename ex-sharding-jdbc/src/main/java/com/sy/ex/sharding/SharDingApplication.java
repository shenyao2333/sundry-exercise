package com.sy.ex.sharding;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: shenyao
 * @Date: Created by 2021/1/3 18:32
 * @description:
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.sy.ex.sharding.mapper"})
public class SharDingApplication {

    public static void main(String[] args) {
        SpringApplication.run(SharDingApplication.class, args);
    }

}
