package com.sy.rockermq.client.one.service;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

/**
 * @Author: shenyao
 * @Date: Created by 2020/12/6 22:14
 * @description:
 */
@Service
public class ReceiveService {

    @StreamListener("input1")
    public void receiveInput1(String receiveMsg) {
        System.out.println("input1 receive: " + receiveMsg);
    }

   // @StreamListener("input2")
   // public void receiveInput2(String receiveMsg) {
   //     System.out.println("input2 receive: " + receiveMsg);
   // }


}
