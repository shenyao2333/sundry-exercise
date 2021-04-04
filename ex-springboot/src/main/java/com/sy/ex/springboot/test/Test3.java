package com.sy.ex.springboot.test;

import java.util.Arrays;

/**
 * @Author: shenyao
 * @Date: Created by 2021/4/3 19:48
 * @description:
 */
public class Test3 {

    public static void main(String[] args) {
        Integer[] a = new Integer[]{1,23,343,2342,3};
        for (int i = 0; i < a.length-1; i++) {
            for (int j = i+1; j < a.length; j++) {
                if (a[i]>a[j]){
                    int num = a[i];
                    a[i] = a[j];
                    a[j] = num;
                }
            }
        }
        System.out.println(Arrays.toString(a));
    }


}
