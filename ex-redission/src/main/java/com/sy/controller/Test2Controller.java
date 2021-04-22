package com.sy.controller;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @Author: shenyao
 * @Date: Created by 2021/4/21 21:03
 * @description:
 */
@RestController
@RequestMapping("/bf")
public class Test2Controller {


    @Resource
    private RedissonClient redissonClient;



    @GetMapping("/test2")
    public Object sdf(){
        Random random = new Random();
        int i = random.nextInt(11);
        RLock test = redissonClient.getLock("test");

        try {
            test.lock();
            test.lock(2,TimeUnit.SECONDS);
            //等待1秒，3秒后自动释放
            boolean b = test.tryLock(7,4,TimeUnit.SECONDS);
            if (b){
                System.out.println(i+"拿到锁");
                System.out.println("进入等待");
                Thread.sleep(6200);
                System.out.println(i+"等待完成");
                return "拿到锁返回";
            }
        } catch (InterruptedException e) {
            System.out.println("异常了");
            return "操作频繁，请等待！";
        }finally {
            if (test.isHeldByCurrentThread()){
                System.out.println(i+"释放锁");
                test.unlock();
            }
        }
        return "未拿到锁！";
    }



}
