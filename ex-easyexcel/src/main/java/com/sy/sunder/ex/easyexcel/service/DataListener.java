package com.sy.sunder.ex.easyexcel.service;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.sy.sunder.ex.easyexcel.domain.ExcelDataDto;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @Author: shenyao
 * @Date: Created by 2020/11/18 21:57
 * @description: 监听器
 */
@Slf4j
public class DataListener extends AnalysisEventListener<ExcelDataDto> {


    private DateMapper dateMapper;

    public DataListener (  DateMapper dateMapper ){
        this.dateMapper =  new DateMapper();
    }

    public DataListener ( ){
        this.dateMapper =  new DateMapper();
    }


    @Override
    public void invoke(ExcelDataDto excelDataDto, AnalysisContext analysisContext) {
        log.info("解析到一条数据："+ excelDataDto);
        dateMapper.test(excelDataDto);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        log.info("所有数据解析完成！");
    }

    /**
     *
     *这里是读取excel的头，可以不重写
     * @param headMap
     * @param context
     */
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        log.info("解析到一条头数据:{}", JSON.toJSONString(headMap));
    }


}
