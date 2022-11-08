package com.sy.ex.grammar.test;

import com.sy.ex.grammar.service.ZoonBehavior;

public class ZoonTest {


    public String sdf(ZoonBehavior zoonBehavior,Integer type,String name){
        String call = zoonBehavior.call(type);

        return  name+ " 在   " +call + "  叫";
    }


    public static void main(String[] args) {

        ZoonTest zoonTest = new ZoonTest();

        String sdf = zoonTest.sdf(s -> s==1? "喵喵":"汪汪" , 1,"小黑");

        System.out.println(sdf);


    }

}
