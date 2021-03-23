package com.sy.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author sy
 * @date: 2021/2/5 10:01
 * @description
 */


@Slf4j
@Component
public class ExecutorConfig {



    @Bean(name = "executorService")
    public  ExecutorService threadPool() {
        int max = Runtime.getRuntime().availableProcessors();
        log.info("初始化线程数："+max);
        return new ThreadPoolExecutor(max / 2 + 1, max,
                0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
    }





}
