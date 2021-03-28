package com.sy.ex.springboot.config;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @Author: shenyao
 * @Date: Created by 2021/3/27 17:02
 * @description:
 */
@Component
public class BootConfig implements ApplicationRunner {


    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("启动执行代码");
    }
}
