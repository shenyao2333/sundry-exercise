package com.sy.ex.springboot.config;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Author: shenyao
 * @Date: Created by 2021/3/28 10:47
 * @description:
 */
public class ExecutorsService {


    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(2);
        newScheduledThreadPool.scheduleAtFixedRate(()->sd(),0,3, TimeUnit.SECONDS);
    }


    public static  void sd(){
        System.out.println("进来了");
    }


}
