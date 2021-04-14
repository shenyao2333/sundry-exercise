package com.sy.ex.springboot.component;

import java.lang.annotation.*;

/**
 * @author shenyao
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperateLog {
    String value() default "无操作！";
}
