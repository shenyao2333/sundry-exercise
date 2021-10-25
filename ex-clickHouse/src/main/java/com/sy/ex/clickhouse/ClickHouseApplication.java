package com.sy.ex.clickhouse;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: sy
 * @Date: Created by 2021/10/25 11:19
 * @description:
 */
@SpringBootApplication
@MapperScan({"com.sy.ex.clickhouse.mapper"})
public class ClickHouseApplication {


    public static void main(String[] args) {
        SpringApplication.run(ClickHouseApplication.class, args);
    }


}
