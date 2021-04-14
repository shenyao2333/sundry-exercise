package com.sy.ex.springboot.test;

import com.sy.ex.grammar.domain.UserInfo;
import com.sy.ex.springboot.service.TestStem;


import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: shenyao
 * @Date: Created by 2021/1/5 21:23
 * @description:
 */
public class Test1 {


    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(30);

        executorService.shutdown();


    }


    public static TestStem by(){
         return  s ->{
             UserInfo userInfo = new UserInfo();
             userInfo.setAge(s);
             userInfo.setUserName("shen要");
             System.out.println("加sadf");
        };
    }


}
