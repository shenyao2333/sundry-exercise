package com.sy.ex.hbase.controller;

import com.sy.ex.hbase.config.HBaseClient;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @Author: sy
 * @Date: Created by 2021/10/13 11:18
 * @description:
 */
@RestController
@AllArgsConstructor
@RequestMapping("/base")
public class HbaseController {

    private final  HBaseClient hBaseClient;



    @GetMapping("/getByKey")
    public Object getByKey(String key) throws IOException {
        return hBaseClient.selectOneRowDataMap("test_create",key);
    }

    @GetMapping("/createTable")
    public void createTable() throws IOException {
         hBaseClient.createTable("test_create","id","name","age");
    }



    @GetMapping("/insertOrUpdate")
    public void insertOrUpdate() throws IOException {
        hBaseClient.insertOrUpdate("test_create","1","name","name1","小明");
    }


}
