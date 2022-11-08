package com.sy.ex.grammar.service.impl;

import com.sy.ex.grammar.service.ZoonBehavior;

public class DogBehior implements ZoonBehavior {
    @Override
    public String call(Integer type) {
        System.out.println("进来");
        return type ==1?"汪汪":"喵喵";
    }
}
