package com.sy.ex.springboot.component;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @Author: shenyao
 * @Date: Created by 2021/4/14 21:49
 * @description:
 */
@Component
@Aspect
public class  OperateAspect {


    /**
     * 关联到自定义的注解上
     */
    @Pointcut(value = "@annotation(com.sy.ex.springboot.component.OperateLog)")
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
     * @param log
     * @return
     * @throws Throwable
     */
    @Around("@annotation(log)")
    public Object around(ProceedingJoinPoint pjp, OperateLog log) throws Throwable {
        System.out.println("请求的数据为："+log.value());
        System.out.println("--调用前--");
        Signature signature = pjp.getSignature();
        System.out.println("调用类："+signature.getDeclaringTypeName());
        System.out.println("切入的类型" + pjp.getKind());
        System.out.println("调用方法名："+signature.getName());
        Object[] args = pjp.getArgs();
        for (Object arg : args) {
            System.out.println("参数值："+arg);
        }

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String url = request.getRequestURL().toString();
        System.out.println("url:"+url);
        //GET 请求其实可以从request里获取出参数
        //Map<String,String[]> map=request.getParameterMap();
        for (Object arg : pjp.getArgs()) {
            System.out.println( "参数值："+arg);
        }
        MethodSignature methodSignature = (MethodSignature) signature;
        // 参数名数组
        String[] parameterNames = methodSignature.getParameterNames();
        System.out.println("参数名数组："+Arrays.asList(parameterNames));
        System.out.println("--调用前结束--");
        return pjp.proceed();
    }






}
