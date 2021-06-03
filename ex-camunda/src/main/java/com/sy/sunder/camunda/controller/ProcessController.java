package com.sy.sunder.camunda.controller;

import com.sy.sunder.camunda.domain.dto.CommonTaskDto;
import com.sy.sunder.camunda.service.ProcessService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.camunda.bpm.engine.rest.dto.task.TaskDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: sy
 * @Date: Created by 2021/6/3 17:22
 * @description: 流程处理控制层
 */
@RestController
@AllArgsConstructor
@RequestMapping("/process")
public class ProcessController {

    private final ProcessService processService;

    @ApiOperation(value = "流程初始化", notes = "流程初始化")
    @PostMapping(value = "/init")
    public List<TaskDto> initProcess(@RequestBody CommonTaskDto taskDto){
        List<TaskDto> dto =   processService.initProcess(taskDto);
        return dto;
    }
}
