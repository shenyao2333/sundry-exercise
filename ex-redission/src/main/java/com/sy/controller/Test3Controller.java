package com.sy.controller;

import lombok.RequiredArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @Author: shenyao
 * @Date: Created by 2021/4/23 21:51
 * @description:
 */
@RestController
@RequestMapping("/test3")
@RequiredArgsConstructor
public class Test3Controller  {


    private final RedissonClient redissonClient;

    @GetMapping("/test1")
    public void dsf() throws InterruptedException {
        RLock lock = redissonClient.getLock("test1");
        lock.lock();
        boolean b = lock.tryLock();
        if (!b){
            System.out.println("拿不到锁");
            return;
        }
        System.out.println("1进来了");
        Thread.sleep( 10000);
        System.out.println("1休眠完成");
        if (lock.isHeldByCurrentThread()){
            lock.unlock();
        }


    }
    @GetMapping("/test2")
    public void test2() throws InterruptedException {
        RLock lock  = redissonClient.getLock("test1");
        lock.lock(1,TimeUnit.MINUTES);
        System.out.println("2进来了");
        Thread.sleep( 100000);
        System.out.println("2休眠完成");
        if (lock.isHeldByCurrentThread()){
            lock.unlock();
        }

    }




}
