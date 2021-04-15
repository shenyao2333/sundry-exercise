package com.sy.ex.springboot.service;

import com.sy.ex.springboot.component.OperateLog;
import org.springframework.stereotype.Service;

/**
 * @Author: shenyao
 * @Date: Created by 2021/4/14 21:45
 * @description:
 */
@Service
public class TestService {


    @OperateLog("测试传入值")
    public String service(String s){
        System.out.println("进来service方法");
        return "方法返回值";
    }



}
