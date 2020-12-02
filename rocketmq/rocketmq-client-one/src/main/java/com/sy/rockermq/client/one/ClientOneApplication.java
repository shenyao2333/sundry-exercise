package com.sy.rockermq.client.one;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;

/**
 * @Author: shenyao
 * @Date: Created by 2020/12/2 22:33
 * @description:
 */
@SpringBootApplication
@EnableBinding({ Source.class, Sink.class })
public class ClientOneApplication {


    public static void main(String[] args) {
        SpringApplication.run(ClientOneApplication.class, args);
    }


}
