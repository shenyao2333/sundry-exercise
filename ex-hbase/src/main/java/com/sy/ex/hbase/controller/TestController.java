package com.sy.ex.hbase.controller;

import com.alibaba.fastjson.JSONObject;
import com.sy.ex.hbase.config.HBaseService;
import com.sy.ex.hbase.domain.UserInfo;
import com.sy.ex.hbase.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: sy
 * @Date: Created by
 * @description:
 */
@RestController
@RequestMapping
@AllArgsConstructor
public class TestController {

    private final UserDto userDto;

    private final HBaseService hbaseService;
    //测试创建表


    @GetMapping("/testCreateTable")
    public void testCreateTable() {
        hbaseService.creatTable("test_base", Arrays.asList("a", "back"));
    }



    @GetMapping("/queryAll")
    public  List<UserInfo> queryAll(){
        return userDto.queryAll();
    }


    @GetMapping("/save")
    public void save(UserInfo userInfo){
        userDto.save(userInfo);
    }


    @GetMapping("/getByParam")
    public List<UserInfo> getByParam(UserInfo userInfo){
       return userDto.getByParam(userInfo);
    }

    @GetMapping("/deleteByName")
    public void deleteByName(String name){
        userDto.deleteByName(name);
    }


    @GetMapping("/createTable")
    public void createTable(){
        userDto.createTable();
    }


    @PostMapping("/executeSql")
    public Object executeSql(@RequestBody JSONObject json){
      return userDto.executeSql((String) json.get("sql"));
    }



}
