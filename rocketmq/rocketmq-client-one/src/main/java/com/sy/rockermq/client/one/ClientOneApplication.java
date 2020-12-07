package com.sy.rockermq.client.one;

import com.sy.rockermq.client.one.rocketmq.StreamClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;


/**
 * @Author: shenyao
 * @Date: Created by 2020/12/2 22:33
 * @description:
 */
@SpringBootApplication
@EnableBinding({StreamClient.class })
class ClientOneApplication {


    public static void main(String[] args) {
        SpringApplication.run(ClientOneApplication.class, args);
    }


}
