package com.sy.regex.pattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: sy
 * @Date: Created by 2022.7.30-18:05
 * @description:
 */
public class TestPattern {


    public static void main(String[] args) {

        test2();
    }


    public static void test1(){

        Pattern pattern = Pattern.compile("\\d*");
        Matcher matcher = pattern.matcher("12b");
        while (matcher.find()){
            System.out.println(matcher.group());
            System.out.println(matcher.start()+"---"+ matcher.end());
        }




    }

    public static void test2(){
        Pattern pattern = Pattern.compile("(?i)Java");
        Matcher matcher = pattern.matcher("java");
        System.out.println(matcher.matches());
    }

    public static void test3(){

    }

    public static void test4(){

    }

    public static void test5(){

    }





}
