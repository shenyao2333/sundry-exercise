package com.sy.ex.hbase.controller;

import com.sy.ex.hbase.service.HBaseService;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @Author: sy
 * @Date: Created by
 * @description:
 */
@RestController
@RequestMapping
public class TestController {
    /**
     * 内部已实现线程安全的连接池
     */
    @Resource
    private Connection hbaseConnection;
    private HBaseService hBaseService;

    @GetMapping("/test")
    public void aaaa() throws IOException {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("lie1");
        hBaseService.creatTable("test22",strings);
    }
}
