package com.sy.ex.springboot.test;

/**
 * @Author: shenyao
 * @Date: Created by 2021/4/6 21:18
 * @description:
 */
public class Test7 {

    private static Object test8;

    public Test7() {
    }

    public static void main(String[] args) {

        String aaabbb = asf("aaabgbbrrr");
        System.out.println(aaabbb);
    }

    public static   String asf(String s){
        char[] chars = s.toCharArray();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < chars.length; i++) {
            int num = 1;
            for (int j = i+1; j < chars.length; j++) {
                if (chars[i]==chars[j]){
                    i++;
                    if (num==1){
                        sb.append(chars[i]);
                    }
                    num++;
                }
            }
            sb.append(num);
        }

        return sb.toString();

    }


}
