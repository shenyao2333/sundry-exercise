package com.sy.ex.springboot.config;

import java.util.HashSet;
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


   //public static void main(String[] args) {
   //    ExecutorService executorService = Executors.newFixedThreadPool(10);

   //    ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(2);
   //    newScheduledThreadPool.scheduleAtFixedRate(()->sd(),0,3, TimeUnit.SECONDS);
   //}


    public static  void main(String[] args){

        String s1 = "Programming";
        String s2 = new String("Programming");
        String s3 = "Program";
        String s4 = "ming";
        String s5 = "Program" + "ming";
        String s6 = s3 + s4;
        System.out.println(s1 == s2);//false
        System.out.println(s1 == s5);//true
        System.out.println(s1 == s6);//true false
        System.out.println(s1 == s6.intern());//true
        System.out.println(s2 == s2.intern());//true false

    }


}
