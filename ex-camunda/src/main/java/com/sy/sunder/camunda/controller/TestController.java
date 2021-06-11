package com.sy.sunder.camunda.controller;

import com.sy.sunder.camunda.domain.dto.CommonTaskDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.camunda.bpm.engine.rest.dto.task.TaskDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: sy
 * @Date: Created by 2021/6/10 18:02
 * @description:
 */
@RestController
@Api(tags = "测试")
public class TestController {


    @GetMapping("test")
    public String initProcess(){
        return "成功了！";
    }



}
