package com.sy.regex.matcher;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: sy
 * @Date: Created by 2022.8.6-17:52
 * @description:
 */
public class FindTest {


    public static void main(String[] args) {

        Pattern compile = Pattern.compile("\\d{0,5}?");
        Matcher matcher = compile.matcher("af123123");

        while (matcher.find()){
            System.out.println("找到匹配词->"+matcher.group());
            System.out.println(matcher.start()+"---"+matcher.end());
        }


    }


}
