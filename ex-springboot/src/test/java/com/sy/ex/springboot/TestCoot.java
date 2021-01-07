package com.sy.ex.springboot;

import com.sy.ex.springboot.config.ConfigTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * @Author: shenyao
 * @Date: Created by 2021/1/7 22:14
 * @description:
 */
@SpringBootTest
public class TestCoot {

    @Resource
    private ConfigTest configTest;

    @Test
    public void asdf(){
        ArrayList<String> userName = configTest.getUserName();

        System.out.println(userName);

    }


}
