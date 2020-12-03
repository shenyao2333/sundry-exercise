package com.sy.rockermq.client.one.controller;

import com.sy.rockermq.client.one.service.TestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: shenyao
 * @Date: Created by 2020/12/3 21:33
 * @description:
 */
@RestController
public class TestController {

    @Resource
    private TestService testService;

    @GetMapping("/test")
    public void sdf(){
        testService.saf();
    }


}
