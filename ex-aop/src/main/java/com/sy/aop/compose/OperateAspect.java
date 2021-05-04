package com.sy.aop.compose;

import com.sy.aop.aspect.OperateLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;


/**
 * @Author: shenyao
 * @Date: Created by 2021/4/14 21:49
 * @description:
 */
@Component
@Aspect
public class OperateAspect {

    /**
     * 关联到自定义的注解上
     */
    @Pointcut("execution(* com.sy.aop.controller..*.*(..))")
    public void access() {
    }

    /**
     * 进来切点世界，先经过的第一个站
     * @param joinPoint
     * @throws Throwable
     */
    @Before("access()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        System.out.println("Before 进来");
    }

    /**
     * 方法结束后执行
     * @param joinPoint
     */
    @After("access()")
    public void after(JoinPoint joinPoint) {
        System.out.println("After 执行" );
    }


    @AfterReturning(pointcut = "access()", returning = "jsonResult")
    public void asdf(JoinPoint joinPoint, Object jsonResult){
        System.out.println("执行完，返回数据："+jsonResult);
    }


    /**
     * 环绕增强，是在before前就会触发
     * @param pjp
     * @param
     * @return
     * @throws Throwable
     */
    @Around("@annotation(log)")
    public Object around(ProceedingJoinPoint pjp,OperateLog log) throws Throwable {
        System.out.println("Around 执行前");
        Object proceed = pjp.proceed();
        System.out.println("Around 执行后");
        return proceed;
    }






}
