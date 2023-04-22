package com.sy.ex.seimi.controller;

import cn.wanghaomiao.seimi.config.SeimiConfig;
import cn.wanghaomiao.seimi.core.Seimi;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: sy
 * @createTime: 2023-04-18 14:40
 * @description:
 */
@RestController
public class BootController {


    @GetMapping("/boot/basic")
    public Object asdf(String  name){
        try {
            Seimi s = new Seimi();
            s.start(name);
            return "成功！";
        }catch (Exception e){
            e.printStackTrace();
            return "失败！";
        }

    }


    @GetMapping("/boot/basic2")
    public Object asdf2(String  name){
        try {
            SeimiConfig config = new SeimiConfig();
            config.setSeimiAgentHost("127.0.0.1");
            //config.redisSingleServer().setAddress("redis://127.0.0.1:6379");
            Seimi s = new Seimi(config);
            s.goRun(name);
            return "成功！";
        }catch (Exception e){
            e.printStackTrace();
            return "失败！";
        }

    }







}
