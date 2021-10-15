package com.sy.ex.hbase;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"com.sy.ex.hbase.hbasedto","com.sy.ex.hbase.mapper"})
public class HbaseApplicatin {

    public static void main(String[] args) {
        SpringApplication.run(HbaseApplicatin.class, args);
    }

}
