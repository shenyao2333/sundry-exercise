## 自定义注解实现AOP操作

### 一、首先依赖aop包

```
<dependency>
	<groupId>org.springframework.boot</groupId>
   	<artifactId>spring-boot-starter-aop</artifactId>
</dependency>
```



### 二、定义一个注解类

该类为@interface类型（接口类型并且前面有个@符号）

```
package com.sy.ex.springboot.component;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperateLog {
    String value() default "无操作！";
}

```

> @Target 、@Retention 、@Documented为固定的，每个都有它的作用

#### 2.1、@Target

指明修饰这个注解的使用范围，使用ElementType属性指明可以在哪个地方能用。

| 取值      | 范围             |
| --------- | ---------------- |
| TYPE      | 类，接口或者枚举 |
| METHOD    | 方法             |
| PARAMETER | 参数             |
| FIELD     | 域，包含枚举常量 |
| FIELD     | 域，包含枚举常量 |

#### 2.2、@Retention 

指定修饰的注解的生命周期，使用RetentionPolicy属性指明生命周期

| 取值    | 周期                                                         |
| ------- | ------------------------------------------------------------ |
| SOURCE  | 源码级别保留，编译后即丢弃。                                 |
| CLASS   | 编译级别保留，编译后的class文件中存在，在jvm运行时丢弃，这是默认值。 |
| RUNTIME | 运行级别保留，编译后的class文件中存在，在jvm运行时保留，可以被反射调用。 |

#### 2.3、@Documented 注解

指明修饰的注解可以被如javadoc此类的工具文档化，只负责标记，没有成员取值。



#### 2.4、@Inherited

声明可以被子类继承的注解





