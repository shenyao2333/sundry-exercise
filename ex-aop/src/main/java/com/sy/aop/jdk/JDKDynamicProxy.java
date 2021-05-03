package com.sy.aop.jdk;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author: shenyao
 * @Date: Created by 2021/5/3 11:24
 * @description: JDK动态代理
 */
public class JDKDynamicProxy  implements InvocationHandler {

    private Object target;

    public JDKDynamicProxy(Object target) {
        this.target = target;
    }
    /**
     * 获取被代理接口实例对象
     * @param <T>
     * @return
     */
    public <T> T getProxy() {
        return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("生成一份" +args[0] +"订单");
        //System.out.println("执行方法名："+method.getName());
        //System.out.println("参数为："+ Arrays.asList(args));
        Object result = method.invoke(target, args);
        System.out.println("生产出来，订单完成!");
        return result;
    }


    public static void main(String[] args) {
        // 保存生成的代理类的字节码文件
        // System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        /**
         *  jdk动态代理测试，可以理解为客户知道某个工厂，找到代理商
         */
        Factory subject = new JDKDynamicProxy(new FactoryImpl()).getProxy();
        /**
         * 客户拿着清单指定需求
         */
        subject.production("螺丝刀");
    }
}
