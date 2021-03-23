package com.sy.controller;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
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


    private static Integer i1 = 10;
    private static Integer i2 = 10;
    private static Integer i3 = 10;
    private static Integer i4 = 10;




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
        boolean res = test.tryLock(3, 10, TimeUnit.SECONDS);
        System.out.println(res);
        return "成功";
    }


    @GetMapping("/test5")
    public String test5() throws InterruptedException {
        ArrayList<HashMap<Integer,Integer>> integers = new ArrayList<>();
        for (int i = 1; i < 11; i++) {
            HashMap<Integer, Integer> map = new HashMap<>(2);
            map.put(i,i);
            integers.add(map);
        }
        HashMap<Integer, Integer> map = new HashMap<>(2);
        map.put()
        Integer num = getNum(1);


        return "成功";
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


    public Integer getNum(int num){
        try {
            Integer ds= 0 ;
            int i =   num%4;
            if (i==0){
              return i1;
            }else if (i==1){
                return i2;
            }else if (i==2){
                return i3;
            }else if (i==3){
                return i4;
            }
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 0;
    }



}
