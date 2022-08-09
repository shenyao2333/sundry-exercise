package com.sy.regex.matcher;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: sy
 * @Date: Created by 2022.8.6-18:08
 * @description:
 */
public class GroupTest {

    public static void main(String[] args) {
        test1();
    }


    public static void test1(){
        Pattern compile = Pattern.compile("((\\d[abc])b)");
        Matcher matcher = compile.matcher("123abc45b6");

        while (matcher.find()){
            System.out.println(matcher.group());
            System.out.println(matcher.group(1));
            System.out.println(matcher.group(2));
        }

    }


    public static  void test2(){
        // 按指定模式在字符串查找
        String line = "123ra9040 123123aj234 adf12322ad 222jsk22";
        String pattern = "(\\d+)([a-z]+)(\\d+)";

        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(line);

        while (m.find( )) {
            // 拿到上面匹配到的数据
            System.out.println("Found value: " + m.group(0) );
            System.out.println("Found value: " + m.group(1) );
            System.out.println("Found value: " + m.group(2) );
            System.out.println("Found value: " + m.group(3) );
        }
    }


}
