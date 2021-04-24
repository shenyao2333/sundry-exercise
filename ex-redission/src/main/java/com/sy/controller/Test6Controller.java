package com.sy.controller;

import com.sy.config.RedisUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: shenyao
 * @Date: Created by 2021/4/24 20:14
 * @description: redis递减操作
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/test6")
public class Test6Controller {


    private final RedisUtil redisUtil;


    @GetMapping("/test1")
    public void test1(){
        redisUtil.set("test61",10);
    }



    @GetMapping("/test2")
    public void sdf(){
        long incr = redisUtil.decr("test61",1);
        System.out.println(incr);
    }



}
