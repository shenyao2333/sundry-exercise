package com.sy.regex.matcher;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: sy
 * @Date: Created by 2022.7.30-18:07
 * @description:
 */
public class Test1 {

    public static void main(String[] args) {

        Pattern compile = Pattern.compile("[0-9]*");
        Matcher matcher = compile.matcher("12");
        boolean status = matcher.matches();

        System.out.println(status);

    }





}
