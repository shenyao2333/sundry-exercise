package com.sy.controller;

import lombok.RequiredArgsConstructor;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @Author: shenyao
 * @Date: Created by 2021/4/24 18:04
 * @description: 布隆过滤器测试
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/test5")
public class Test5Controller {


    private final RedissonClient redissonClient;


    @GetMapping("/test1")
    public void asdf(){
        RBloomFilter<Object> bloomFilter = redissonClient.getBloomFilter("url:sd");
        boolean b = bloomFilter.tryInit(1000L, 0.01);
        bloomFilter.expire(3000, TimeUnit.DAYS);
        System.out.println("创建是否成功："+b);
        bloomFilter.add("123");
        bloomFilter.add("456");
        bloomFilter.add("789");

        System.out.println(bloomFilter.contains("123"));
        System.out.println(bloomFilter.contains("456"));
        System.out.println(bloomFilter.contains("789"));
        System.out.println(bloomFilter.contains("6767"));
    }


}
