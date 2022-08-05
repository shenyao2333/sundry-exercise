package com.sy.regex.matcher;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: sy
 * @Date: Created by 2022.8.5-17:50
 * @description:
 */
public class LookingAtTest {


    public static void main(String[] args) {

        Pattern compile = Pattern.compile("\\d{0,3}");
        Matcher matcher = compile.matcher("ddds");

        System.out.println(matcher.lookingAt());



    }

}
