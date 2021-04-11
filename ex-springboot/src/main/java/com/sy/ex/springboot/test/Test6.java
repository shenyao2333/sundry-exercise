package com.sy.ex.springboot.test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: shenyao
 * @Date: Created by 2021/4/4 11:45
 * @description:
 */
public class Test6 {
    public static void main(String[] args) {
        ArrayList<Integer> integers = new ArrayList<>();

        Object[] objects = integers.toArray();

        List<Object> objects1 = Arrays.asList(objects);

        int array[] = {1,2,3,4};

        int length = array.length;




    }

}
