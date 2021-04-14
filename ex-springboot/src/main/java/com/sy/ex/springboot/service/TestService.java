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


    @OperateLog("开始调用")
    public String service(){
        System.out.println("进来service方法");
        return "service返回";
    }



}
