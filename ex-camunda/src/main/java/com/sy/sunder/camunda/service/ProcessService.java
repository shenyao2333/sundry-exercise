package com.sy.sunder.camunda.service;

import com.sy.sunder.camunda.domain.dto.CommonTaskDto;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.rest.dto.task.TaskDto;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: sy
 * @Date: Created by 2021/6/3 17:18
 * @description:
 */
@Service
@AllArgsConstructor
public class ProcessService  {


    private final TaskService taskService;
    private final RuntimeService runtimeService;
    private final IdentityService identityService;

    public List<TaskDto> initProcess(CommonTaskDto taskDto) {
        String processInstanceId = null;
        Map<String, Object> variables = taskDto.getVariables();

        variables.put("starter", taskDto.getStarter());

        // 流程初始化
        ProcessInstance processInstance = null;
        // 流程初始化
        if (StringUtils.isNotBlank(taskDto.getProcessDefKey())) {
            processInstance = runtimeService.startProcessInstanceByKey(taskDto.getProcessDefKey(),
                    variables);
        } else {
            processInstance = runtimeService.startProcessInstanceById(taskDto.getProcessDefId(),
                    variables);
        }
        List<TaskDto> resultList = new ArrayList<TaskDto>();
        // 创建成功
        if (processInstance != null && StringUtils.isNotBlank(processInstance.getId())) {
            processInstanceId = processInstance.getId();
            resultList = simpleGetTasks(processInstanceId);
            taskService.complete(resultList.get(0).getId(),taskDto.getVariables());
            taskService.createComment(resultList.get(0).getId(), processInstanceId, "提交流程");
        }
        return resultList;
    }


    public List<TaskDto> simpleGetTasks(String processInstId){
        List<TaskDto> resultList = new ArrayList<TaskDto>();
        List<Task> taskList = taskService.createTaskQuery().processInstanceId(processInstId).list();
        for (Task task : taskList) {
            TaskDto dto = new TaskDto();
            dto = TaskDto.fromEntity(task);
            resultList.add(dto);
        }
        return resultList;
    }
}
