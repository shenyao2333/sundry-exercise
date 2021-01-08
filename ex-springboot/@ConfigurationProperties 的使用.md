## @ConfigurationProperties 的使用

### 一、前言

​	spring3.0新增的注解，提供了Jav配置的能力，使用Java配置更好的理解配置的bean。SpringIOC的一个核心概念——Bean。由Spring容器来负责对Bean的实例化、装配和管理。XML是用来描述Bean很流行的配置方式。但随着Spring的日益发展，“Spring项目大量的烂用XML”就是最为严励的一个批评。因为Spring会把多有的业务类都已Bean的形式配置在XML文件中，造成了大量的XML文件。使用xml来配置Bean失去了编译时的类型安全检查，这使得整个项目变得更加复杂了。

​	JavaEE5.0的发布，引入了一个非常重要的特性——Annotations（注释）。注释就是源代码的标签，这些标签可以在源代码层进行处理或通过编译器把它融入到class文件中。

​	JavaConfing就是使用注释来描述Bean配置的组件。JavaConfig是Spring的一个子项目，比起Spring，他还是个年轻的项目。目前是1.0M2。



### 二、ConfigurationProperties简介	

```
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ConfigurationProperties {
    @AliasFor("prefix")
    String value() default "";

    @AliasFor("value")
    String prefix() default "";

    boolean ignoreInvalidFields() default false;

    boolean ignoreUnknownFields() default true;
}
```

​	Spring源码中大量使用ConfigurationProperties注解，比如sever.prot就是由该注解获取到的，还可以通过与其他注解配合使用，能够实现Bean的按需配置。

​	注解通过prefix属性指定前缀，绑定配置文件中的配置，可以放在类上，也可以放在方法上。放在类上或者方法上都需要被Spring托管（使用@Configuration或者@Component）

​	简单一句话就是Spring的有限运行是通过上下文（Bean容器）中的Bean的配合完成。Bean是对象，我们先把它变成一个bean，然后与配置文件绑定，最后将此Bean归还给容器。



### 三、使用

​	与@Value注解相似，它是通过全限定名进行配置绑定，ConfigurationProperties其实就类似于使用多个@Value同时绑定，所以对象属性和类的字段名称相同，- 分割的可以使用驼峰命名的方式。

#### 例如

- properties文件

  ```
  spring.datasource.druid.write.url=write
  spring.datasource.druid.write.username=root
  spring.datasource.druid.read.url=read
  spring.datasource.druid.read.username=root
  ```

- bean对象

  ```
  @Configurable
  @Component
  public class BeanConfig  {
  
      @ConfigurationProperties(prefix = "spring.datasource.druid.read")
      @Bean(name = "readDruidDataSource")
      @Primary
      public DataSource readDruidDataSource(){
          return new DataSource();
      }
      
      @ConfigurationProperties(prefix = "spring.datasource.druid.write")
      @Bean(name = "writeDruidDataSource")
      public DataSource writeDruidDataSource(){
          return new DataSource();
      }
      
  }
  ```

  > 这里将@ConfigurationProperties(prefix = "spring.datasource.druid.read")放在了方法上，如果不放在方法上则可以放在DataSource对象上

- 对象

  ```
  @Data
  public class DataSource {
      private String url;
      private String username;
  }
  ```

  

- 测试，这里就可以拿到了

  ```
  @Resource
  private ApplicationContext applicationContext;
   @Test
      public void test2(){
          DataSource readDruidDataSource =(DataSource) this.applicationContext.getBean("writeDruidDataSource");
          System.out.println(readDruidDataSource);
      }
  ```



| 属性                | 说明                                                         |      |
| ------------------- | ------------------------------------------------------------ | ---- |
| prefix              | 指定配置文件的前缀                                           |      |
| ignoreInvalidFields | Bean与配置文件绑定报错了会起动不起来，设为true则忽略         |      |
| ignoreUnknownFields | prefix指定了前缀，Bean对象申明了属性，如果配置文件与Bean对象的属性对不上，且设为false时，启动就会报错。 |      |
| @Validated          | 这个是另外一个的，代表着注入Bean为空时报错                   |      |

