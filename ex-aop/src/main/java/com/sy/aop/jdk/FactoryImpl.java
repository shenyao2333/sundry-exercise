package com.sy.aop.jdk;

/**
 * @Author: shenyao
 * @Date: Created by 2021/5/3 11:22
 * @description:
 */
public class FactoryImpl implements Factory {


    @Override
    public void production(String msg) {
        System.out.println("工厂开始生产："+ msg);
    }
}
