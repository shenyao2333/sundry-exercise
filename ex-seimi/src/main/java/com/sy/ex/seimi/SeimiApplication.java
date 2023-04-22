package com.sy.ex.seimi;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author: sy
 * @createTime: 2023-04-18 14:30
 * @description:
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class,
        scanBasePackages = "com.sy")
public class SeimiApplication {


    public static void main(String[] args) {
        SpringApplication.run(SeimiApplication.class, args);
    }



}
