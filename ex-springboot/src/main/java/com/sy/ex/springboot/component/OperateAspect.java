package com.sy.ex.springboot.component;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

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

    //进来切点世界，先经过的第一个站
    @Before("access()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        System.out.println("");
    }


    //环绕增强，是在before前就会触发
    @Around("@annotation(OperateLog)")
    public Object around(ProceedingJoinPoint pjp, OperateLog s) throws Throwable {
        System.out.println("-aop 日志环绕阶段-" + new Date());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

//        GET 请求其实可以从request里获取出参数
//       Map<String,String[]> map=request.getParameterMap();
//        System.out.println("获取参数："+map.get("username")[0])
        String url = request.getRequestURL().toString();
        String logTrackValue = logTrack.value();
        Object[] pipArrary = pjp.getArgs();
        if (pipArrary.length>1){ //多参,不是Map/JsonObject方式
            List<Object> argList = new ArrayList<>();
            for (Object arg : pjp.getArgs()) {
                // request/response无法使用toJSON
                if (arg instanceof HttpServletRequest) {
                    argList.add("request");
                } else if (arg instanceof HttpServletResponse) {
                    argList.add("response");
                } else {
                    argList.add(JSON.toJSON(arg));
                }
            }
            Signature signature = pjp.getSignature();
            MethodSignature methodSignature = (MethodSignature) signature;
            // 参数名数组
            String[] parameterNames = ((MethodSignature) signature).getParameterNames();
            System.out.println("参数名数组："+new ArrayList(Arrays.asList(parameterNames)));
            System.out.println("参数是："+argList.toString());
            System.out.println("logTrackValue:"+logTrackValue);
            System.out.println("url:"+url);
            System.out.println("ip:"+ip);
            return pjp.proceed();

        }

        Object param =  pipArrary[0];
        System.out.println("logTrackValue:"+logTrackValue);
        System.out.println("url:"+url);
        System.out.println("ip:"+ip);
        System.out.println("param:"+param.toString());
        return pjp.proceed();

    }


    /**
     * 方法结束后执行
     * @param joinPoint
     */
    @After("access()")
    public void after(JoinPoint joinPoint) {
        System.out.println("After 执行" );
    }




}
