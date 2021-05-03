package com.sy.aop.controller;

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
    public void test1(){
        System.out.println("test1调用");
    }



}
