package com.sy.ex.guava.term;



/**
 * @Author: sy
 * @Date: Created by 2022.7.10-10:25
 * @description: 匹配器
 */
public abstract class CharMatcher {

    /**
     * 比较方法
     * @param var1
     * @return
     */
    public abstract boolean matches(char var1);

    public CharMatcher or(CharMatcher other) {
        return new Or(this, other);
    }


    private static final class Or extends CharMatcher {

        final CharMatcher first;
        final CharMatcher second;

        Or(CharMatcher a,CharMatcher b) {
            this.first = a;
            this.second = b;
        }

        @Override
        public boolean matches(char var1) {
            return first.matches(var1)||second.matches(var1) ;
        }
    }

    private static final class And extends CharMatcher {

        final CharMatcher first;
        final CharMatcher second;

        And(CharMatcher a,CharMatcher b) {
            this.first = a;
            this.second = b;
        }

        @Override
        public boolean matches(char var1) {
            return first.matches(var1) && second.matches(var1);
        }


    }







}
