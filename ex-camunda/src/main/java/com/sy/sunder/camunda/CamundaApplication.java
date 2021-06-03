package com.sy.sunder.camunda;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.camunda.bpm.spring.boot.starter.event.PostDeployEvent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.EventListener;

import javax.annotation.Resource;

/**
 * @Author: sy
 * @Date: Created by 2021/4/20 10:39
 * @description:
 */

@SpringBootApplication
@EnableProcessApplication
public class CamundaApplication {

    @Resource
    private RuntimeService runtimeService;

    public static void main(String[] args) {
        SpringApplication.run(CamundaApplication.class, args);
    }



}
