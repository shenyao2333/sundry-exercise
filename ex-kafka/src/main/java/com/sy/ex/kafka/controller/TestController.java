package com.sy.ex.kafka.controller;

import com.sy.ex.kafka.producer.KafkaProducer;
import lombok.AllArgsConstructor;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;

/**
 * @Author: sy
 * @Date: Created by 2021/7/9 15:38
 * @description:
 */
@RestController
@RequestMapping
public class TestController {

    @Resource
    private  KafkaProducer kafkaProducer;

    @Resource
    private  AdminClient adminClient;

    @GetMapping("/test")
    public void test(String msg,String topic){
        kafkaProducer.send(msg,topic);
    }

   /**
    * 查看topic列表
    */
   @GetMapping("/test2")
   public  void test2() throws ExecutionException, InterruptedException {
       ListTopicsResult listTopicsResult = adminClient.listTopics();
       // 获取所有topic的名字，返回的是一个Set集合
       Set<String> topicNames = listTopicsResult.names().get();
       System.out.println(topicNames);
   }


   ///**
   // * 创建topic
   // */
   //@GetMapping("/test3")
   //public  void test3(){
   //    ArrayList<NewTopic> topics = new ArrayList<>();
   //    NewTopic nowTopic = new NewTopic("nowTopic", 2, (short) 1);
   //    topics.add(nowTopic);
   //    adminClient.createTopics(topics);

   //}




}
