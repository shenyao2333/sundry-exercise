package com.sy.ex.guava.list;

import com.google.common.collect.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Author: sy
 * @Date: Created by 2022-01-18-20:50
 * @description:
 */
public class Test1 {


    public static void main(String[] args) {
        ImmutableSet<String> sdf = ImmutableSet.of("sdf", "123", "343");
        ImmutableList<Object> of = ImmutableList.of("1", "324", "1", 12);
        ImmutableList.Builder<String> s = ImmutableList.<String>builder().add("S");
        s.add("sdf");
        ImmutableList<String> build = s.build();




        ImmutableMultimap<String, String> of1 = ImmutableMultimap.of("dsf", "sdf");





    }




}
