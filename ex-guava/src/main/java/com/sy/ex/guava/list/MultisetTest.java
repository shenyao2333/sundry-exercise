package com.sy.ex.guava.list;

import com.google.common.collect.*;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * @Author: sy
 * @Date: Created by 2022-01-19-21:15
 * @description:
 */
public class MultisetTest {


    public static void main(String[] args) {

        String str = "this is this a cat and that is a mice";
        String[] strArr = str.split(" ");

        List<String> words = Arrays.asList(strArr);

        Multiset<String> wordMultiset = HashMultiset.create();
        wordMultiset.addAll(words);



        wordMultiset.add("is");
        wordMultiset.add("is");
        wordMultiset.add("is");
        wordMultiset.setCount("is", 2);
        System.out.println(wordMultiset.count("is"));
    }


}
