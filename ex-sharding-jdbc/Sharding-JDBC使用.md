

## ShardingSphere-JDBC

### 一、概述

ShardingSphere-JDBC定位为轻量级 Java 框架，是在JDBC层提供的额外服务，以Jar包提供服务，也就是直接maven依赖就能使用，它有如下优势：

- 完全兼容JDBC和各种ORM框架（如JPA、Hibernate、Mybatis、SpringJDBCTemplate或者JDBC）。
- 支持任何第三方的数据库连接池，如：DBCP、C3P0、Druid、Hikaricp等
- 支持任意实现JDBC规范的数据库，如：MySQL、Oracle、SQLServer、PostgreSQL等。



### 二、背景

一般的数据集中存储在单一节点上，在数据量超过阈值的情况下，由于大多关系型数据库使用B+类型的索引，索引深度增加使得磁盘访问的IO次数增加，进而导致查询性能的下降，成为系统的最大瓶颈，并且落在数据库的压力也会大大提升，系统就可能会挂了。

关系型数据库无法满足互联网场景需要的情况下，NoSQL的尝试也越来越多。但对SQL的不兼容以及生态圈的不完善，有时也不能满足我们自己的需求。所以只能在关系型数据库上想办法，所以分片就产生了。



### 三、分片概述

数据分片指按照将存放在一个地方的数据分散存放至多个数据库或者表中以达到提升性能瓶颈以及可用性的效果。数据分片有效手段是对关系型数据库进行分库分表，分库和分表可以避免由数据量超过阈值而产生的性能瓶颈，分库还能分散对数据库单点的访问。分表虽然不能缓解数据库的压力，但却能够提供尽量将分布式事务转化为本地事务的可能，因为涉及到跨库的更新操作，分布式事务往往会使用问题变得复杂

#### 3.1、垂直分片

垂直分片，又称为纵向拆分，理念是专库专用，一般按照业务进行划分归类，分散到多个表或者多个库

#### 3.2、水平分表

又称为横向拆分，将数据分散至多个库或表中

#### 3.3、挑战

分片解决了数据库的问题，但在开发过程就麻烦很多了，例如，分表导致表名称的修改，或者分页、排序、聚合分组等操作的不正确处理。

跨库事务也是分布式的数据库集群要面对的棘手事情。 合理采用分表，可以在降低单表数据量的情况下，尽量使用本地事务，善于使用同库不同表可有效避免分布式事务带来的麻烦。 在不能避免跨库事务的场景，有些业务仍然需要保持事务的一致性。

而**ShardingSphere** **就是为了让使用方尽量像使用一个数据库一样使用水平分片之后的数据库集群**



### 四、Sharding分片

#### 4.1、分片策略

| 策略     | 策略名       | 说明                                                         |
| -------- | ------------ | ------------------------------------------------------------ |
| standard | 标准分片策略 | 自定义一个类，当遇到分片字段后触发执行返回数据源（表或者数据库） |
| complex  | 复合分片策略 | 与standard一样，但可以定义多个分片字段                       |
| hint     | 强制路由     | 不根据指定的字段来判断取哪个库或者表，而是在业务处理时直接指定库或者表，省去了sql解析的过称。 |
| inline   | inline表达式 | 使用标识行表达式，${ expression } 或者 $->{ expression }     |
| none     | 无分片策略   |                                                              |

#### 4.2、分片算法

> 当使用不同的分片策略则会对应着不同的分片算法。

| 分片算法                     |               |
| ---------------------------- | ------------- |
| PreciseShardingAlgorithm     | 精确分片      |
| RangeShardingAlgorithm       | 范围分片      |
| ComplexKeysShardingAlgorithm | 复合分片      |
| HintShardingAlgorithm        | 非SQL解析分片 |



### 五、SpringBoot集成Sharding实现分片

#### 5.1、maven环境

> 其他的依赖忽略

```java
 <dependency>
     <groupId>org.apache.shardingsphere</groupId>
     <artifactId>sharding-jdbc-spring-boot-starter</artifactId>
     <version>4.1.1</version>
 </dependency>
```



#### 5.2、inline表达式

> 使用简单的表达式，根据取模或者一些简单的数字来分片信息

```yaml
spring:
  # 不加上会报错
  main:
    allow-bean-definition-overriding: true
  shardingsphere:
    datasource:
      # 定义两个库，名称随便起
      names: tb0,tb1
      tb0:
        type: com.zaxxer.hikari.HikariDataSource
        jdbc-url:  xxx
        username: xxx
        password: xxx
        max-active: 16
        pool-name: tb0HikariCP
        minimum-idle: 5
      tb1:
        type: com.zaxxer.hikari.HikariDataSource
        jdbc-url: xxxx
        username: xxx
        password: xxx
        max-active: 16
        pool-name: tb1HikariCP
        minimum-idle: 5
    sharding:
      binding-tables: user_info
      tables:
        #绑定表
        #逻辑表名
        user_info:
          actual-data-nodes: tb$->{0..1}.user_info_$->{0..3}
          #主键生成策略, SNOWFLAKE和 UUID 默认为 SNOWFLAKE
          key-generator:
            column: id
            type: SNOWFLAKE
          #分表规则
          table-strategy:
            inline:
              shardingColumn: age
              algorithm-expression: user_info_$->{age % 4}
          databaseStrategy:
            inline:
              shardingColumn: age
              algorithmExpression: tb$->{age % 2}
```

这里的想法是先按照年龄来分库，在分表，把年龄为双数的放在tb0里，单数的放tb1库里。 然后在按照年龄与4取模放在user_info_0、user_info_1、user_info_2、user_info_3四个表中。这里先不管分片的缺陷，就意思意思下。使用inline表达式用法简单，方便，只需要在配置文件中简单指定即可，但我认为在实战中并不会满足我们的需求，所以重点应该在后面的几种方案里。



#### 5.3、standard标准分片策略

**yml配置文件**

```yaml
spring:
  main:
    allow-bean-definition-overriding: true
  shardingsphere:
    # 范围查询允许全库全表查询
    props:
      sql:
        # 显示sql，默认为false
        show: false
      allow.range.query.with.inline.sharding: true
    datasource:
      names: tb0,tb1
      tb0:
        type: com.zaxxer.hikari.HikariDataSource
        jdbc-url: xxx
        username: xxxx
        password: xxx
        max-active: 16
        pool-name: tb0HikariCP
        minimum-idle: 5
      tb1:
        type: com.zaxxer.hikari.HikariDataSource
        jdbc-url: xxxx
        username: xxx
        password: xx
        max-active: 16
        pool-name: tb1HikariCP
        minimum-idle: 5
    sharding:
      #绑定表
      binding-tables: user_info
      tables:
        #逻辑表名
        user_info:
          actual-data-nodes: tb$->{0..1}.user_info_$->{0..3}
          #主键生成策略, SNOWFLAKE和 UUID 默认为 SNOWFLAKE
          key-generator:
            column: id
            type: SNOWFLAKE
          #分表规则
          table-strategy:
            standard:
              shardingColumn: phone
              # 当执行sql时解析绑定的逻辑表后执行下面的类
              preciseAlgorithmClassName: com.sy.ex.sharding.config.StandardTableShardingAlgorithm
              rangeAlgorithmClassName: com.sy.ex.sharding.config.StandardTableShardingAlgorithm
          databaseStrategy:
            standard:
              shardingColumn: age
              preciseAlgorithmClassName: com.sy.ex.sharding.config.StandardDataShardingAlgorithm
              rangeAlgorithmClassName: com.sy.ex.sharding.config.StandardDataShardingAlgorithm
```

> preciseAlgorithmClassName 为精准分片算法，是处理=和in条件的查询
> rangeAlgorithmClassName为范围分片算法，处理  >  <   between 的条件查询。
>
> 这两种都需要自己实现，这样能适应不同业务的。



**分片逻辑类**

> 需要实现PreciseShardingAlgorithm和RangeShardingAlgorithm类

```java
package com.sy.ex.sharding.config;

import com.google.common.collect.Range;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;
import org.springframework.stereotype.Component;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: sy
 * @Date: Created by 2021/6/21 10:36
 * @description:  Standard标准分片数据库选择逻辑
 */
@Component
public class StandardDataShardingAlgorithm implements PreciseShardingAlgorithm<Integer> ,RangeShardingAlgorithm<Integer>  {
    
    private final Integer  boundariesAge = 18;

    //精准分片
    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Integer> preciseShardingValue) {
        Integer value = preciseShardingValue.getValue();
        String s = this.getTb(value);
        for (String tb : collection) {
            if (tb.endsWith(s)){
                return tb;
            }
        }
        return collection.iterator().next();
    }


    //范围分片
    @Override
    public Collection<String> doSharding(Collection<String> collection, RangeShardingValue<Integer> rangeShardingValue) {
        Set<String> restSet = new HashSet<>(4);
        Range<Integer> valueRange = rangeShardingValue.getValueRange();
        if ( valueRange.hasUpperBound()){
            Integer upper = valueRange.upperEndpoint();
            if (upper<=this.boundariesAge){
                restSet.add("tb0");
            }else {
                restSet.add("tb1");
            }
        }else {
            restSet.add("tb1");
        }
        if ( valueRange.hasLowerBound()){
            Integer lower = valueRange.lowerEndpoint();
            if (lower>=this.boundariesAge){
                restSet.add("tb1");
            }else {
                restSet.add("tb0");
            }
        }else {
            restSet.add("tb0");
        }
        return restSet;
    }

    public String getTb(Integer value){
        String s = "tb1";
        if (value <= this.boundariesAge){
            s = "tb0";
        }
        return s;
    }
}
```

```java
package com.sy.ex.sharding.config;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @Author: sy
 * @Date: Created by 2021/6/21 10:36
 * @description: Standard标准分片表选择逻辑
 */
@Component
public class StandardTableShardingAlgorithm implements PreciseShardingAlgorithm<String> ,RangeShardingAlgorithm<String>  {

    /**
     *
     * @param collection 分片表集合
     * @param preciseShardingValue 分片依据对象信息
     * @return 真实表名
     */
    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<String> preciseShardingValue) {
        String tableSuffix = this.getTableSuffix(preciseShardingValue.getValue());
        for (String tableName : collection) {
            if (tableName.endsWith(tableSuffix)){
                return tableName;
            }
        }
        return collection.iterator().next();
    }



    private String getTableSuffix(String value){
        if (value==null){
            return "0";
        }
        String substr = value.substring(value.length()-1);
        try {
            int num = Integer.parseInt(substr);
            if (num<2){
                return "0";
            }else if (num<=5){
                return "1";
            }else if (num<=7) {
                return "2";
            }else {
                return "3";
            }
        }catch (Exception e){
            return "0";
        }
    }

    /**
     * 获取数据时判断取哪个库
     * @param collection
     * @param rangeShardingValue
     * @return 范围时 真实表集合
     */

    @Override
    public Collection<String> doSharding(Collection<String> collection, RangeShardingValue<String> rangeShardingValue) {
        System.out.println(rangeShardingValue);
        System.out.println(rangeShardingValue.getValueRange());
        return collection;
    }


}

```

这里的想法是先按照年龄来分库，在按照手机号分表。把小于18的用户放在tb0里，大于18的放在tb1中。然后在根据手机结尾的数字来反散到四个表中。



#### 5.4、复合分片策略



#### 5.5、hint分片

yaml配置
```yaml
spring:
  main:
    allow-bean-definition-overriding: true
  shardingsphere:
    # 范围查询允许全库全表查询
    props:
      sql:
        # 显示sql，默认为false
        show: true
    datasource:
      names: tb0,tb1
      tb0:
        type: com.zaxxer.hikari.HikariDataSource
        jdbc-url: xxx
        username: xxx
        password: xxx
        max-active: 16
        pool-name: tb0HikariCP
        minimum-idle: 5
      tb1:
        type: com.zaxxer.hikari.HikariDataSource
        jdbc-url: xxx
        username: xxx
        password: xxx
        max-active: 16
        pool-name: tb1HikariCP
        minimum-idle: 5
    sharding:
      #绑定表
      binding-tables: user_info
      tables:
        #逻辑表名
        user_info:
          actual-data-nodes: tb$->{0..1}.user_info_$->{0..3}
          #主键生成策略, SNOWFLAKE和 UUID 默认为 SNOWFLAKE
          key-generator:
            column: id
            type: SNOWFLAKE
          #分表规则
          tableStrategy:
            hint:
              algorithmClassName: com.sy.ex.sharding.config.HintTableAlgorithm
          databaseStrategy:
            hint:
              algorithmClassName: com.sy.ex.sharding.config.HintDataAlgorithm
              

```



配置类

```java

package com.sy.ex.sharding.config;

import org.apache.shardingsphere.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.hint.HintShardingValue;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Author: sy
 * @Date: Created by 2021/6/21 16:37
 * @description:
 */
@Component
public class HintDataAlgorithm implements  HintShardingAlgorithm {


    @Override
    public Collection<String> doSharding(Collection collection, HintShardingValue hintShardingValue) {
        List<String> shardingResult = new ArrayList<>();
        Collection<String> values = hintShardingValue.getValues();
        for (Object value : values) {
            for (Object value2 : collection) {
                if (value2.toString().endsWith(value.toString())){
                    shardingResult.add(value2.toString());
                }
            }
        }
        return shardingResult;
    }

}

```

**使用案例**

```java
       try (HintManager hintManager = HintManager.getInstance()) {

            /**
             * 添加分片库，为什么选择库需要使用逻辑分片表名呢？
             * 因为是使用user_info绑定了分片逻辑，需要靠他来触发绑定的HintDataAlgorithm类
             * 添加进去后，触发HintDataAlgorithm类后根据value来确定使用哪个库（可以为多个库）
             */
            hintManager.addDatabaseShardingValue("user_info", "0");

            /**
             * 添加分片表，原理和上面是一样的，也是根据value来确定要使用哪些表。
             */
            hintManager.addTableShardingValue("user_info", "_0");
            hintManager.addTableShardingValue("user_info", "_1");
            hintManager.addTableShardingValue("user_info", "_2");

            // 直接指定对应具体的数据库，使用value来触发HintDataAlgorithm类确定要哪个库
            //hintManager.setDatabaseShardingValue(1);
            
            // System.out.println(userInfoService.list());
            userInfoService.save(userInfo);
        }
```



### 六、读写分离

**配置类**

```yaml
spring:
  main:
    allow-bean-definition-overriding: true
  shardingsphere:
    props:
      sql:
        # 显示sql，默认为false
        show: true
    sharding:
      # 配置数据库主从关系，可以多主多从
      masterSlaveRules:
        #随便定义的一个名字
        masterSlave1:
          masterDataSourceName: tb0
          slaveDataSourceNames: tb1
          # 算法，ROUND_ROBIN 轮询， RANDOM 随机
          loadBalanceAlgorithmType: ROUND_ROBIN
    datasource:
      names: tb0,tb1
      tb0:
        type: com.zaxxer.hikari.HikariDataSource
        jdbc-url: xxx
        username: xx
        password: xxxx
        minimum-idle: 10
        max-active: 400
        idle-timeout: 600000
        pool-name: tb0HikariCP
        connection-timeout: 30000
      tb1:
        type: com.zaxxer.hikari.HikariDataSource
        jdbc-url: xxx
        username: xxx
        password: xxx
        minimum-idle: 10
        max-active: 400
        idle-timeout: 600000
        pool-name: tb1HikariCP
        connection-timeout: 30000
```

