package com.sy.controller;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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
        RLock test = redissonClient.getLock("test");
        try {
            //等待1秒，2秒后自动释放
            boolean b = test.tryLock(2,4,TimeUnit.SECONDS);
            if (b){
                Thread.sleep(3000);
                System.out.println("拿到锁");
                return "拿到锁返回";
            }
        } catch (InterruptedException e) {
            System.out.println("异常了");
            return "操作频繁，请等待！";
        }finally {
            if (test.isHeldByCurrentThread()){
                test.unlock();
            }
        }
        return "未拿到锁！";
    }



}
