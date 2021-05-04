## AOP

### 前言

​	在学习AOP的时候被很多术语、方法和框架给搞懵。仿佛就知道AOP是个切面编程，执行方法前先执行某方法，执行完后再执行某方法。但学习不能知其然，不知其所以然。想快速理清概念，掌握AOP思想，找到合适的应用AOP场景。这里应该先理清楚AOP思想才是重点。而我们主要是要使用SpringAOP，那么就不得不先去了解它一些相关的知识，层层递进，最终到SpringAOP实战。

### 一、AOP认识

先举个例子，设计一个日志打印的模块，按照OOP思想，先设计一个工具类，然后在需要打印的地方引用即可。刚开始没有任何问题，但是如果当调用的很多，到处都能看得到。从对象组织角度来讲，我们一般会使用继承关系为主线，我们可以称为**纵向**，也就是OOP。如果设计时只使用OOP思想可能会带来两个问题：
1、都采用纵向思维，如果这个时候考虑这些不同对象的共性，不仅会增加设计的难度和复杂性，还会造成类的接口过多而难以维护（共性越多，意味着接口的契约越多）
2、需要对现有的对象动态增加某种行为或者责任时非常困难。

而AOP就可以很好的解决以上的问题，除了纵向分类之外，我们从横向的角度去观察这些对象，无需在去到处调用了，说明哪些地方需要打印日志，这个地方就是一个切面，AOP会在适时的时机把打印语句插进切面。

如果说OOP是把问题划分到单个模块的话，那么AOP就是把涉及到众多模块的某一类问题进行统一管理。AOP的目标是把这些功能集中起来，放到一个统一的地方来控制和管理。利用AOP思想，这样对业务逻辑的各个部分进行隔离，从而降低业务逻辑各部分的耦合，提高程序的可用性和开发效率。

![](https://note.youdao.com/yws/public/resource/8f913335f04c11f8ab2e43b477d18d2c/xmlnote/9F962B70B37A43AC8DEC10A34DE4297B/19787)



**OOP与 AOP的区别 **

思想上，OOP是纵向结构，AOP是横向结构。OOP注重是对业务逻辑单元的划分，AOP偏重业务处理过程中的某个步骤或者阶段。他们两者之间是一个互相补充和完善的关系。



### 二、JDK 动态代理

​	SpringAOP默认将标准JDK动态代理用于AOP代理，也可以使用CGLIB代理，如果对象未实现接口则默认情况下使用CGLIB代理，最好的做法是对接口进行编程，而不是对类进行编程，因为一个业务类通常将实现一个或多个业务接口。我们最终的目的是使用SpringAOP，而它又基于JDK动态代理和CGLIB代理，那么我们就有必要学习这两个东西。

​	首先先给一个场景辅助我们理解这次动态代理的思路。工厂，代理商，顾客，功能清单。当顾客要买某个东西的时候不能直接去工厂买，只能找代理商买，代理商可以给客户一个清单，然后代理商根据清单让工厂生产东西拿给顾客。

#### 2.1、动态代理

 **实现步骤**

- 1、创建接口类，定义方法（可以看做是一个清单）
- 2、创建业务对象，实现接口类（它将做为被代理的真实对象）
- 3、创建增强器。实现InvocationHandler接口，把真实对象包裹进来。（它可以当做代理商）
- 4、使用代理类调用真实的业务类。代理对象已经将真实的对象包裹进来了，所以能做一些前置或者后置操作。代理类使用**Proxy**来拿到，后面具体讲。



**功能清单**

```java
package com.sy.aop.compose;

/**
 * @Author: shenyao
 * @Date: Created by 2021/5/3 11:21
 * @description:功能清单
 */
public interface Factory {

    void production(String msg);
}

```

**实际工厂类**

```java
package com.sy.aop.compose;

/**
 * @Author: shenyao
 * @Date: Created by 2021/5/3 11:22
 * @description:
 */
public class FactoryImpl implements Factory {


    @Override
    public void production(String msg) {
        System.out.println("工厂开始生产："+ msg);
    }
}


```

**代理类**

```java

package com.sy.aop.compose;
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
        /**
         * 通过Proxy类的newProxyInstance方法创建代理对象
         * 第一个参数：使用handler对象的classloader对象来加载我们的代理对象
         * 第二个参数：这里为代理类提供的接口是真实对象实现的接口，这样代理对象就能像真实对象一样调用接口中的所有方法
         * 第三个参数：handler，我们将代理对象关联到上面的InvocationHandler对象上
         */
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

```

> 运行main方法，得到--》
>
> 生成一份螺丝刀订单
> 工厂开始生产：螺丝刀
> 生产出来，订单完成!



#### 2.2、总结

刚才使用的东西陌生的有两个**Proxy**和**InvocationHandler**。

**Proxy** 介绍：

我们需要生成一个代理对象，这个代理对象具体代理谁是一个动态的，正常我们是通过一个构造方法new出来，但是现在我们需要一个动态根据具体实现类得到一个代理类。而在JDK1.5之后提供了一个"**java.lang.reflect.Proxy**"类，只需要调用**newProxyInstance**方法就可以得到某一个对象的代理对象了（里面使用了反射的原理）。



**InvocationHandler**介绍：

每一个动态代理类的调用处理程序都必须实现InvocationHandler接口，并且每个代理类的实例都关联到了实现该接口的动态代理类调用处理程序中，当我们通过动态代理对象调用一个方法时候，这个方法的调用就会被转发到实现InvocationHandler接口类的invoke方法来调用，所以我们必须要实现invoke方法，在这个方法里我们就可以做一些前置或者后置的操作了。



从刚才的demo里我们可以了解到客户调用代理对象的方法都是调用invoke方法，就等于拦截了客户的动作了，并且知道客户调用的是什么方法，传入的参数等。这样我们就可以实现一些特殊的需求，如检查客户有没有资格，或者对客户的需求进行一下改变等额外的功能。这也就是JDK的动态代理。



### 三、cglib动态代理

CGLIB动态代理是利用asm开源包，对代理对象类的class文件加载进来，通过修改其字节码生成子类来处理。

这里不详细的说明，只是了解一下它与JDK代理的区别是什么

**JDK动态代理只能对实现了接口的类生成代理，而不能针对类（反射）**

**CGLIB是针对类实现代理，主要是对指定的类生成一个子类，覆盖其中的方法（继承）**

如果目标对象实现了接口，默认情况下会采用JDK的动态代理实现AOP。否则就是使用CGLIB库，spring就是在这两者之间转换。如果对象实现了接口，也可以强制使用CGLIB。而没实现接口不能使用JDK的动态代理。





### 四、AspectJ介绍

#### 4.1、介绍

AspectJ是一个java实现的AOP框架，采用静态织入对Java代码进行AOP编译，让Java代码具有AOP功能。它是目前AOP框架里最成熟、功能最丰富的语言，而且与Java完全兼容，它采用的是静态织入。使用AspectJ就是先定义一个切面类，然后使用pointcut定义切点，pointcut定义需要应用切面的方法，然后再定义before之类的通知函数让目标方法在执行之前执行。如果想要了解更多

[官网]: https://www.eclipse.org/aspectj/



#### 4.2、术语

AspectJ它定义了几个专业AOP术语，而SpringAOP也沿用这些术语，所以也很重要。如下介绍几个。

| 关键字    | 术语     | 说明（顺序有含义）                                           |
| --------- | -------- | ------------------------------------------------------------ |
| JoinPoint | 连接点   | 在程序中可以被调用的方法都是joinPoint。仿佛就是菜单上的选项  |
| Pointcut  | 切点     | 使用正则表达式确定切点，一般使用函数call()或者execution()编写正则表达式。它的作用就是从joinPoint中挑选多个方法作为切点。 |
| Advice    | 通知     | 切入点上需要执行哪些操作。包含了五个before、after等，等下具体说 |
| Aspect    | 切面     | 切点和通知结合而成。                                         |
| weaving   | 织入     | 把切面的代码与实际调用函数的融合过程。                       |
| Target    | 横切对象 | 也可以当成一个被代理的对象。                                 |



#### 4.3、通知函数

当切入点进来时，它的执行动作和顺序的函数都定义好了，总共有五个

| 关键字          | 说明                                                         |
| --------------- | ------------------------------------------------------------ |
| before          | 目标方法执行前执行，前置通知                                 |
| after           | 方法执行后执行，后置通知                                     |
| after returning | 目标方法返回时执行 ，后置返回通知                            |
| after throwing  | 目标方法抛出异常时执行 异常通知                              |
| around          | 在目标函数执行中执行，可控制目标函数是否执行，环绕通知。在before前面执行。可以通过proceed方法控制目标函数是否执行。 |



#### 4.4、总结

当我们了解了AspectJ后，它与SpringAOP的关系是什么？ Spring 使用了AspectJ 提供的用于切入点与匹配库来实现AOP功能，就是说使用了AspectJ的思想和术语。SpringAop采用的动态织入，在运行将要增强的代码织入到目标类中，这个过程就是刚才讲的动态代理。AspectJ是静态织入，编译的时候就在class文件中织入了。



### 五、SpringAOP

#### 5.1、介绍

Spring AOP 与ApectJ 的目的一致，都是横切业务处理，但与AspectJ不同的是，它不需要特殊的编译过程，不需要控制类加载器的层次结构。SpringAOP 功能通常是与 Spring IoC 容器结合使用。使用常规的 bean 定义语法来配置切面。在非常细粒度的对象(通常是域对象)AspectJ 是最佳选择。Spring可以无缝地将 Spring AOP 、IoC和ApectJ 融合，只是SpringAOP基本满足我们的需求了，开发中直接使用Spring中提供的AOP功能即可。Spring AOP 与 AspectJ不是竞争关系，它们两者是互补的。


#### 5.2、案例

> 这个是基于SpringBoot的案例

**1、maven环境**

```
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-aop</artifactId>
</dependency>
```

**2、切面类**

> 有些用法后面讲

```java
package com.sy.aop.compose;

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
     * 正则表达式，拦截com.sy.aop.controller包或者子包中定义的所有方法
     */
    @Pointcut("execution(* com.sy.aop.controller..*.*(..))")
    public void access() {
    }

    /**
     * 切点进来执行方法
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
    public void afterReturning(JoinPoint joinPoint, Object jsonResult){
        System.out.println("执行完，返回数据："+jsonResult);
    }


    /**
     * 环绕增强，是在before前就会触发
     * @param pjp
     * @param
     * @return
     * @throws Throwable
     */
    @Around("execution(* com.sy.aop.controller..*.*(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("Around 执行前");
		Object proceed = pjp.proceed();
        System.out.println("Around 执行后");
        return proceed;
    }


}

```

**3、测试类**

```java
package com.sy.aop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test2Controller {
    
    @GetMapping("/test1")
    public void test1(){
        System.out.println("test1调用");
    }
    
}
```

**4、结果**

```
Around 执行前
Before 进来
test1调用
Around 执行后
After 执行
执行完，返回数据：null

————---------------------------------------------
方法调用只打印了一句话，现在已经执行了多个方法，这里需要注意around执行了两次。已经很明显的感受到了AOP的功能了，就是因为这一系列执行就可以拿来做更多的事情了，比如执行前做权限校验、参数校验，执行后做日志打印等等。后面具体讲一下这几个注解的使用。
```

所以得到结果执行顺序如下：

![image-20210504111640524](https://note.youdao.com/yws/public/resource/028a52a3f5c6b0210ef7eba9e9d3a3ac/xmlnote/9FEBDB6AB6B84A5BB5A2C08CC65B2B3B/19800)



#### 5.3、切点pointcut

pointcut主要是放表示式，匹配需要拦截的方法，它里面可以放多个关键字

##### 5.3.1、execute(常用)

- 根据包路径拦截方法，但是这里拦截的不包含子包中的方法

  ```java
  //表示式格式： 方法修饰符  方法所在包路径   ()前面匹配方法名，里面意味着参数
  execution(* com.sy.aop.controller.*.*(..))
  ```

- 根据包路径拦截方法，这里拦截了controller包下所有的方法，包含了子包的方法。也可以指定到特定的类上。

  ```java
  execution(* com.sy.aop.controller..*.*(..))
  ```

  > 我是这么理解的：第一个是匹配修饰符的如public， 接一个空格放包路径，其中controller后面的第二个'..星'，代表着controller这个包下的方法，与上面（只匹配无子包下的方法）对比多了一个' . ' ，所有这个'.'有着特别含义， 那么得到加' . ' 的会匹配到包下的包和包下的方法，没加的只会匹配无子包的方法。

  

- 拦截任意公共方法

  ```java
  execution(public * *(..))
  ```

- 拦截service开头的方法

  ```java
  execution(* service*(..))
  ```

#####  5.3.2、within

与execution有些类似，感觉只是简化了一下

- 拦截包中任意方法，不包含子包的方法

  ```java
  within(com.sy.aop.controller.*)
  ```

- 包下所有方法，包含子包的方法

  ```java
  within(com.sy.aop.controller..*.*)
  ```

  > 这个多余的点含义和上面是一样的

##### 5.3.3、this

- 指定一个类，当调用到这个类时会拦截。我觉得用不到

  ```java
  this(com.sy.aop.controller)
  ```

##### 5.3.4、atgs

- 匹配方法中的参数,这里只拦截了controller下只有一个参数的方法。

  ```java
  @Pointcut("args(com.sy.aop.controller)")
  ```

- 拦截多个参数的方法

  ```java
  @Pointcut("args(com.sy.aop.controller,..)")
  ```

##### 5.3.5、@target

- 如果这个对象包含了某个注解那么就会被拦截。只要这个类有这个注解，所有的方法都拦截

  ```java
  @target(com.sy.aop.compose.Annotation)
  ```

##### 5.3.5、@annotation(常用)

- 匹配用了这个注解的方法

  ```java
  @annotation(com.sy.aop.compose.Annotation)
  ```

  

#### 5.4、@Around环绕增强

这里它放的也是一个表达式，并且规则也和pointcut一样的。

```java
@Around("execution(* com.sy.aop.controller..*.*(..))")
public Object around(ProceedingJoinPoint pjp) throws Throwable {
    System.out.println("Around 执行前");
    Object proceed = pjp.proceed();
    System.out.println("Around 执行后");
    return proceed;
}
```



##### JoinPoint介绍

这个对象包含了切入相关的信息，如切入点对象、方法、属性等。通过它提供的方法直接获取。在每个AOP步骤里都有看到它。所以还是特别常用的

常用API

```java
/**
* 返回目标对象，即被代理的对象
*/
Object getTarget();

/**
* 获取切入点的参数
*/
Object[] getArgs();

/**
* Signature 主要包含了切入点方法名、包名等
*/
Signature getSignature();

/**
* 返回切入的类型
*/
 String getKind();

```



##### ProceedingJoinPoint介绍

点进去这个对象就会发现ProceedingJoinPoint是继承JoinPoint对象的。那么ProceedingJoinPoint的作用是什么呢？它在JoinPoint基础上暴露了proceed 方法，这个是**aop代理链执行**的方法，能决定是否走代理链还是走自己拦截的其他逻辑。

> 环绕通知=前置+目标方法执行+后置通知，proceed方法就是用于启动目标方法执行的



##### 注解形式

> @annotation 放的是自定义的注解类，但是这里的格式是放方法里引用这个注解类，然后放它的别名进来。
>
> [如何自定义注解可以参考这篇](https://blog.csdn.net/qq_39381892/article/details/116402387?spm=1001.2014.3001.5501)

```java
    @Around("@annotation(log)")
    public Object around(ProceedingJoinPoint pjp,OperateLog log) throws Throwable {
        System.out.println("注解值传进来："+log.value());
        System.out.println("Around 执行前");
        Object proceed = pjp.proceed();
        System.out.println("Around 执行后");
        return proceed;
    }
```

#### 5.5、总结

等等，为什么直接到总结呢，因为关于其他的注解使用都是一样的，只需要关注它们的执行顺序。所以只讲了pointcut和Around。





### 六、总结

我们要使用SpringAOP，我们需要先了解AOP思想开始、到动态代理、然后AspectJ、最后才到SpringAOP。在我看来这几个东西都不可少，AOP思想是为了解决某些问题才产生的（应用场景），动态代理是SpringAOP的底层运用，用法上主要借鉴了AspectJ，并且能与它完美融合，最终用一个简单的demo来展示了SpringAOP的实现过程，关于一些复杂的业务需要自己填充。这是我的一个AOP学习笔记，也分享出来，如果有问题，希望指出！





## 参考文献

```

https://www.cnblogs.com/bigmonkeys/p/7823268.html

https://blog.csdn.net/yaomingyang/article/details/80981004

#Spring官方文档
https://www.docs4dev.com/docs/zh/spring-framework/4.3.21.RELEASE/reference/aop.html#aop-introduction

# 切点注解
https://www.cnblogs.com/itsoku123/p/10744244.html

https://blog.csdn.net/javazejian/article/details/56267036

# JointPoint 说明
https://blog.csdn.net/kouryoushine/article/details/105299956
```

