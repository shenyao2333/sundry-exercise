package com.sy.ex.sharding.controller;

import com.github.pagehelper.PageHelper;
import com.sy.ex.sharding.domain.UserInfo;
import com.sy.ex.sharding.mapper.UserInfoMapper;
import com.sy.ex.sharding.service.UserInfoService;
import lombok.AllArgsConstructor;
//import org.apache.shardingsphere.api.hint.HintManager;
//import org.apache.shardingsphere.api.sharding.hint.HintShardingValue;
import org.apache.shardingsphere.api.hint.HintManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 * @Author: sy
 * @Date: Created by 2021/6/18 10:24
 * @description:
 */
@RestController
@AllArgsConstructor
public class TestController {


    private final UserInfoService userInfoService;
    private final UserInfoMapper userInfoMapper;

    @PostMapping("/add")
    public void add(@RequestBody UserInfo userInfo){
        userInfoService.save(userInfo);
    }


    @GetMapping("/list")
    public Object list(){
        return userInfoService.list();
    }


    @GetMapping("/scope")
    public List<Object> scope(Integer age){
        PageHelper.startPage(0,3);
        return  userInfoMapper.getByAge(age);
    }


    @GetMapping("/del")
    public void del(Integer age){
        userInfoMapper.del();
    }

    @PostMapping("/get")
    public List<Object> getByParam(@RequestBody UserInfo userInfo){
       return   userInfoMapper.getByAll(userInfo);
    }


    @PostMapping("add2")
    public void add2(@RequestBody UserInfo userInfo) {

        HintManager hintManager = HintManager.getInstance();
        hintManager.addDatabaseShardingValue("tb0",1);
        hintManager.addDatabaseShardingValue("tb1",2);

        hintManager.addTableShardingValue("user_info1",1);

        hintManager.setDatabaseShardingValue(1);
        userInfoService.save(userInfo);

    }

}
