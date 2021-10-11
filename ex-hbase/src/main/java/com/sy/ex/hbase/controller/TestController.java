package com.sy.ex.hbase.controller;

import com.alibaba.fastjson.JSONObject;
import com.sy.ex.hbase.domain.UserInfo;
import com.sy.ex.hbase.dto.UserDto;
import lombok.AllArgsConstructor;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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


    @GetMapping("/queryAll")
    public  List<UserInfo> queryAll(){
        return userDto.queryAll();
    }


    @PostMapping("/save")
    public void save(@RequestBody UserInfo userInfo){
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


    @GetMapping("/test")
    public Object test(){
       return "成功";
    }

    @GetMapping("/saveList")
    public void saveList(){
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        for (int i = 2973; i < 2000000; i++) {
            UserInfo userInfo = new UserInfo();
            userInfo.setUserId(i);
            userInfo.setName(UUID.randomUUID().toString());
            userInfo.setPhone(this.getStr());
            userInfo.setAge(1);
            userInfo.setSex(i%2==0?"男":"女");
            executorService.execute(
                    ()->userDto.save(userInfo));

        }

    }

    @GetMapping("/getStr")
    public String getStr(){
        return RandomStringUtils.randomNumeric(12);
    }



}
