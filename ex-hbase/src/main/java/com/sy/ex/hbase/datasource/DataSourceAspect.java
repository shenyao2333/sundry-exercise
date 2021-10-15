package com.sy.ex.hbase.datasource;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


/**
 * 多数据源处理
 * 
 * @author admin
 */
@Aspect
@Order(1)
@Component
public class DataSourceAspect {

    @Around("execution(* com.sy.ex.hbase.hbasedto.*.*(..))")
    public Object around(ProceedingJoinPoint point) throws Throwable{
        DSContextHolder.setDSType("phoenix");
        try  {
            return point.proceed();
        } finally  {
            // 销毁数据源 在执行方法之后
            DSContextHolder.clearDSType();
        }
    }


}
