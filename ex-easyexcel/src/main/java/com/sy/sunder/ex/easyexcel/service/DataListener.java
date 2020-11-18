package com.sy.sunder.ex.easyexcel.service;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.sy.sunder.ex.easyexcel.domain.ExeclDataDto;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * @Author: shenyao
 * @Date: Created by 2020/11/18 21:57
 * @description: 监听器
 */
@Slf4j
public class DataListener extends AnalysisEventListener<ExeclDataDto> {


    private DateMapper dateMapper;

    public DataListener (  DateMapper dateMapper ){
        this.dateMapper =  new DateMapper();
    }

    public DataListener ( ){
        this.dateMapper =  new DateMapper();
    }


    @Override
    public void invoke(ExeclDataDto execlDataDto, AnalysisContext analysisContext) {
        log.info("解析到一条数据："+execlDataDto);
        dateMapper.test(execlDataDto);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        log.info("所有数据解析完成！");
    }


}
