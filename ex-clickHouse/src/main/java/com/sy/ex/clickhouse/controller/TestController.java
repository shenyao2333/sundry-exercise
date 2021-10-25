package com.sy.ex.clickhouse.controller;
import java.util.ArrayList;
import java.util.Date;

import com.sy.ex.clickhouse.domain.UserInfo;
import com.sy.ex.clickhouse.service.UserInfoService;
import com.sy.ex.clickhouse.utils.CreatRoundChain;
import lombok.AllArgsConstructor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: sy
 * @Date: Created by 2021/10/25 14:10
 * @description:
 */
@RestController
@RequestMapping("/test")
@AllArgsConstructor
@MapperScan({"com.sy.ex.clickhouse.mapper"})
public class TestController {

    private final UserInfoService userInfoService;


    @GetMapping("/createTable")
    public String createTable(){
        userInfoService.createTable();
        return "成功";
    }

    @GetMapping("/addUserInfo")
    public void addUserInfo(){
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(1);
        userInfo.setName("小明");
        userInfo.setPhone("12312322");
        userInfo.setAge(21);
        userInfo.setSex(1);
        userInfo.setIcon("dfdsfsdfsdfsd");
        userInfo.setOpenid("gsdf3324234");
        userInfo.setCreateTime(new Date());
        userInfoService.save(userInfo);
    }



    @GetMapping("/addUserInfoList")
    public void addUserInfoList(){
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        executorService.execute( ()->{
            for (int i = 1; i < 3; i++) {
                asdf(i);
            }
        });
    }

    private void asdf(int num){
        Random random=new Random();
        ArrayList<UserInfo> dataList = new ArrayList<>();
        int init =  num*14389989;
        int max =  init+14389989;
        for (int i =init; i < max ; i++) {
            UserInfo userInfo = new UserInfo();
            userInfo.setUserId(i);
            userInfo.setName(CreatRoundChain.getName());
            userInfo.setPhone(i+random.nextInt(100)+"");
            userInfo.setAge(random.nextInt(3));
            userInfo.setSex(random.nextInt(60));
            userInfo.setIcon(UUID.randomUUID().toString());
            userInfo.setOpenid(UUID.randomUUID().toString());
            userInfo.setCreateTime(new Date());
            dataList.add(userInfo);
            if (dataList.size()>=3000){
                userInfoService.saveBatch(dataList);
                dataList.clear();
            }
        }
        userInfoService.saveBatch(dataList);
    }






}
