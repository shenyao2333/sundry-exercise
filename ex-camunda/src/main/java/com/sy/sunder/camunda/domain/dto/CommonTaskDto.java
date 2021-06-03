package com.sy.sunder.camunda.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Map;

/**
 * @Author: sy
 * @Date: Created by 2021/6/3 17:08
 * @description:
 */
@Data
@ApiModel
public class CommonTaskDto {

    @ApiModelProperty(value="流程定义ID")
    private String	processDefId;

    @ApiModelProperty(value="流程定义Key")
    private String	processDefKey;

    @ApiModelProperty(value="启动者")
    private String	starter;

    @ApiModelProperty(value="流程标题")
    private	String	title;

    @ApiModelProperty(value="外部业务系统数据主键标识值")
    private String processBusinessKey;

    @ApiModelProperty(value="流程变量键值对")
    private Map<String, Object> variables;



}
