## Kafka

### 一、概念

#### 1.1、Kafka的功能

Kafka是个分布式流处理平台，有如下功能：

- 1、可以发布和订阅流式的记录。这一方面与消息列队或者企业消息系统类型
- 2、储存流失记录，并且具有较好的容错性
- 3、可以让你发布和订阅流

#### 1.2、它是如何工作

kafka作为一个集群，运行在一台或者多台服务器上，通过topic对存储的数据进行分类。每条记录包含一个key，一个value和一个timestamp（时间戳）

#### 1.3、 四个核心的API

- 使用**Producer API**发布一串流式的数据到一个或者多个kafka topic
- 使用**Consumer API** 订阅一个或者多个topic，并且对发布给它的流式数据进行处理

-  使用 **Streams API** 充当一个流处理器，消费一个或者多个topic产生的输入流，然后生产一个输出流到一个或多个topic中去。在输入输出流中进行有效的转换。
- 使用**Connector API**充当一个连接器，可以构建和运行可重复使用的生产者或者消费者，将Topic连接到应用程序或者数据系统，比如，连接到一个关系型数据库，捕捉表（table）的所有变更内容。



#### 1.4、Topics和日志

​	Topic 就是数据主题，记录发布地方，用来区分业务系统，对于每一个Topic，Kafka集群都会维护一个分区日志。每个分区都是有序且顺序不可变的记录集，并且不断追加到结构化的commit log文件。分区中的每个记录都分配一个id来表示顺序，我们使用offset，offset用来唯一的标识分区中的每条记录。

​	Kafka集群保留所有发布的记录，无论他们是否已被消费，然后通过一个可配置的参数来确定保留的期限。举个例子，如果设置保留两天，这条记录在两天内可以随时被消费，两天后这条记录就会被删除并且释放内存。Kafka的性能与数据大小无关，所以长时间存储数据没有什么问题。

​	在每个消费者中唯一保存的元数据是offset（在这里理解为偏移量）即消费在log中的位置。由消费者控制，在读取记录后，以线性的方式增加偏移量。消费者也可以通过重置到某个旧的偏移量，从而处理过去的数据，也可以跳过最近的记录。

#### 1.5、使用

##### 1.5.1、作为消息系统

支持广播和列队消息，Kafka相比于传统消息队列还具有更严格的顺序保证

##### 1.5.2、作为消息系统作为存储系统

Kafka可以作为一个优秀的存储系统，数据写入Kafka后被写到磁盘，并且进行备份以便容错。直到完全备份，Kafka才让生产者认为完成写入。可以存储大量数据，并且可通过客户端控制它读取数据的位置，您可认为Kafka是一种高性能、低延迟、具备日志存储、备份和传播功能的分布式文件系统。

##### 1.5.3、流处理

Kafka 流处理不仅仅用来读写和存储流式数据，它最终的目的是为了能够进行实时的流处理。在Kafka中，流处理器不断地从输入的topic获取流数据，处理数据后，再不断生产流数据到输出的topic中去。简单的数据处理可以直接用生产者和消费者的API。对于复杂的数据变换，Kafka提供了Streams API。 Stream API 允许应用做一些复杂的处理，比如将流数据聚合或者join。

##### 1.5.4、流处理

将消息、存储和流处理结合起来，像HDFS这样的分布式文件系统可以存储用于批处理的静态文件。 一个系统如果可以存储和处理历史数据是非常不错的。



### 二、安装

#### 2.1、下载地址

```http
http://kafka.apache.org/downloads
```

#### 2.3、修改的配置

```properties
#broker实例标识，集群时要保证唯一
broker.id=1

# kafka存放数据的目录
log.dirs=./data/kafka-logs

# 注册中心zookeeper的地址
zookeeper.connect=localhost:2181

# 监听ip，需要保证消费者能访问
listeners=PLAINTEXT://172.17.15.243:9092


```











```
kafka启动命令
./bin/kafka-server-start.sh config/server.properties
# 后台启动
./bin/kafka-server-start.sh -daemon config/server.properties


zookeeper启动
./bin/zookeeper-server-start.sh config/zookeeper.properties
# 后台启动
./bin/zookeeper-server-start.sh -daemon config/zookeeper.properties
```







### 三、SpringBoot集成使用







>
>
>参考文献
>[中文官方文档](https://kafka.apachecn.org/documentation.html#introduction)
>
>
>
>