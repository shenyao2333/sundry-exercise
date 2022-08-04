package com.sy.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: sy
 * @Date: Created by 2022.7.30-18:04
 * @description:
 */
public class Test {

    public static void main(String[] args) {

        test3();

    }

    public static void test1(){
        Pattern compile = Pattern.compile("\\bas");
        Matcher matcher = compile.matcher("asdfgashjjf as");
        while (matcher.find()){

            System.out.println("找到了->"+matcher.group());
        }
    }





    public static void test2(){
        Pattern compile = Pattern.compile("夫$");
        Matcher matcher = compile.matcher("sdf klj 年后夫哦夫 的萨芬,士大夫");
        while (matcher.find()){
            System.out.println(matcher.group());
        }
    }


    public static void test3(){
        Pattern compile = Pattern.compile("\\b([a-z]+) \\1\\b");
        String str = "Is is the cost of of gasoline going up up";
        Matcher matcher = compile.matcher(str);
        while (matcher.find()){
            System.out.println(matcher.group());
        }
    }

    public static void test4(){

    }


    public static void test5(){

    }


    public static void test6(){

    }
}
