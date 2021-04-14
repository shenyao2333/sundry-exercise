package com.sy.ex.springboot.controller;

import com.sy.ex.springboot.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: shenyao
 * @Date: Created by 2021/4/14 21:44
 * @description: 测试的控制层
 */
@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {


    private final TestService testService;


    @GetMapping
    public void test1(){
        System.out.println();
        String service = testService.service();
        System.out.println("调完："+ service);
    }




}
