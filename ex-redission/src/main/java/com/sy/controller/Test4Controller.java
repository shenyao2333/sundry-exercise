package com.sy.controller;

import lombok.RequiredArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Author: shenyao
 * @Date: Created by 2021/4/23 22:15
 * @description:
 */
@RestController
@RequestMapping("/test4")
@RequiredArgsConstructor
public class Test4Controller {


    private final ExecutorService executorService;
    private final RedissonClient redissonClient;


    @GetMapping("/test1")
    private void test1(){
        long l = System.currentTimeMillis();
        for (int i = 0; i < 5; i++) {
            test(i);
        }
        System.out.println(System.currentTimeMillis() - l);
    }


    @GetMapping("/test2")
    private void test2(){
        long l = System.currentTimeMillis();
        for (int i = 0; i < 5; i++) {
            test2(i);
        }
        System.out.println(System.currentTimeMillis() - l);
    }







    private synchronized void   test(int i){
        System.out.println(i  + "进来了");
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private void   test2(int i){
        RLock test1 = redissonClient.getLock("test1");

            //boolean b = test1.tryLock(3, TimeUnit.SECONDS);
        test1.lock();

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        test1.unlock();
    }



}
