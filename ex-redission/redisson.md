```java



RLock test = redissonClient.getLock("test");
boolean b = test.tryLock(12,8,TimeUnit.SECONDS);
```

 尝试拿锁，



redission

#### RLock锁API

- 加锁

```java
/**
 * 加锁 锁的有效期默认30秒
 */
void lock();
```



- 加锁

```
/**
 * leaseTime 锁的有效期
 */
void lock(long leaseTime, TimeUnit unit);
```

> 第一个加锁，锁时间默认为30秒，这个可以设置锁的有效时间



- 获取锁

```java
boolean tryLock();
```

> 带有返回值，获取成功则返回true，失败则false（锁被其他线程占用）



- 获取锁，有等待时间

  ```java
  /**
   * time 等待时间
   * unit 时间单位 小时、分、秒、毫秒等，如分：TimeUnit.SECONDS
   */
  boolean tryLock(long time, TimeUnit unit) throws InterruptedException;
  ```

  > 获取锁，如果拿不到锁会等待一定时间，在这时间内拿到锁返回true，一直拿到不就返回false



- 获取锁，有等待时间，

  ```java
  /**
   * waitTime 等待时间
   * leaseTime 超时时间
   * unit 时间单位 小时、分、秒、毫秒等，如分：TimeUnit.SECONDS
   */
  boolean tryLock(long waitTime, long leaseTime, TimeUnit unit) throws InterruptedException;
  ```

  > 与上一个对比，差别是获取到锁后，有个超时时间，超过了则释放锁。



- 解锁

  ```java
  /**
   * time 等待时间
   * unit 时间单位 小时、分、秒、毫秒等，如分：TimeUnit.SECONDS
   */
  void unlock();
  ```



- 中断锁

  ```java
  void lockInterruptibly();
  ```

  > 表示锁可以被中断，在拿锁或者等待锁的时候，可以调用该方法中断等待



- 检验该锁是否被线程使用，如果被使用返回True

  ```java
   boolean isLocked();
  ```

  > 检查该锁是否被线程使用，如果有返回true



- 检查当前线程是否使用这把锁

  ```java
   boolean isHeldByCurrentThread();
  ```

  