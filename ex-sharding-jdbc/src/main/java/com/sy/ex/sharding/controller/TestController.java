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
import java.util.Map;

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
        try (HintManager hintManager = HintManager.getInstance()) {

            //添加分片库，为什么选择库需要使用逻辑分片表名呢？ 因为是使用user_info绑定了分片逻辑，需要靠来来出
            hintManager.addDatabaseShardingValue("user_info", "0");

            //添加分片表
            hintManager.addTableShardingValue("user_info", "_0");
            hintManager.addTableShardingValue("user_info", "_1");
            hintManager.addTableShardingValue("user_info", "_2");


            //hintManager.addTableShardingValue("user_info", 1);

            // 直接指定对应具体的数据库
            //hintManager.setDatabaseShardingValue(1);
            //在读写分离数据库中，Hint 可以强制读主库（主从复制是存在一定延时，但在业务场景中，可能更需要保证数据的实时性）
            // System.out.println(userInfoService.list());
            userInfoService.save(userInfo);
        }



    }

}
