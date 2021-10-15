package com.sy.ex.hbase.controller;

import com.sy.ex.hbase.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: sy
 * @Date: Created by 2021/10/15 16:14
 * @description:
 */
@RestController
@AllArgsConstructor
@RequestMapping("/mysql")
public class MysqlController {
    private final UserMapper userMapper;


    @GetMapping("get")
    public Object getUser(){
        return  userMapper.selectById(1);
    }



}
