package com.sy.aop.controller;

import com.sy.aop.aspect.OperateLog;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: shenyao
 * @Date: Created by 2021/5/3 19:30
 * @description:
 */
@RestController
public class Test2Controller {

    @GetMapping("/test1")
    @OperateLog("注解调用")
    public void test1(){
        System.out.println("test1调用");
    }


    @GetMapping("/test2")

    public void test2(String s){
        System.out.println("一个参数的调用");
    }


}
