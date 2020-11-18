package com.sy.sunder.ex.easyexcel.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Author: shenyao
 * @Date: Created by 2020/11/18 21:58
 * @description:
 */
@Data
public class ExeclDataDto  {

    /**
     * 强制读取第三个 这里不建议 index 和 name 同时用，要么一个对象只用index，要么一个对象只用name去匹配
     */
    @ExcelProperty(index = 0)
    private Integer xuhao;
    /**
     * 用名字去匹配，这里需要注意，如果名字重复，会导致只有一个字段读取到数据
     */
    @ExcelProperty("IBU")
    private String ibu;


    @ExcelProperty("更新指标时间")
    //@DateTimeFormat("yyyy年MM月dd日HH时mm分ss秒")
    //@DateTimeFormat("yyyy年MM月dd日HH时mm分ss秒")
    private Date date;



}
