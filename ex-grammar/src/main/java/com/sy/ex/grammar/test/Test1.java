package com.sy.ex.grammar.test;

import com.sy.ex.grammar.domain.UserInfo;
import com.sy.ex.grammar.service.TestStem;

/**
 * @Author: shenyao
 * @Date: Created by 2021/1/5 21:23
 * @description:
 */
public class Test1 {


    public static void main(String[] args) {
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
