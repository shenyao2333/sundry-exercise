package com.sy.controller;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Author: shenyao
 * @Date: Created by 2021/3/23 21:56
 * @description:
 */
@RestController
public class TestController {

    @Resource
    private RedissonClient redissonClient;

    @Resource
    private ExecutorService executorService;


    Integer[] data = new Integer[]{6,5,5,5};





    @GetMapping("/test")
    public String test(){
        return "成功！";
    }



    @GetMapping("/test3")
    public String test2(){
        for (int i = 0; i < 10; i++) {
            executorService.execute(() ->sd());
        }
        return "成功！";
    }


    @GetMapping("/test4")
    public String sdf() throws InterruptedException {
        RLock test = redissonClient.getLock("test");
        boolean res = test.tryLock(3,  TimeUnit.SECONDS);
        System.out.println(res);
        return "成功";
    }


    @GetMapping("/test5")
    public String test5() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(21);
        for (int i = 0; i < 21; i++) {
            int finalI = i;
            executorService.execute(()->sds(finalI,countDownLatch));
        }
        countDownLatch.await(200,TimeUnit.SECONDS);
        System.out.println(Arrays.toString(data));
        return "成功";
    }

    public void sds(Integer i, CountDownLatch countDownLatch){
        int suo =  i % 4;
        RLock lock = redissonClient.getLock("suo" + suo);
        try {
            boolean b = lock.tryLock(3, 1000, TimeUnit.SECONDS);
            if (b){
                Integer datum = data[suo];
                Thread.sleep(2200);
                data[i % 4] =  datum-1;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            countDownLatch.countDown();
            lock.unlock();
        }

    }



    public void sd(){
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName()+": "+ i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
    }





}
