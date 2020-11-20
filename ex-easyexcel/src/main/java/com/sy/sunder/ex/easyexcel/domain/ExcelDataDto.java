package com.sy.sunder.ex.easyexcel.domain;
import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.write.style.*;
import com.sy.sunder.ex.easyexcel.service.NumCharConverter;
import lombok.Data;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;

import java.util.Date;

/**
 * @Author: shenyao
 * @Date: Created by 2020/11/18 21:58
 * @description:
 */
@Data
@ColumnWidth(25)//列宽，可作用在属性上。
@HeadRowHeight(20)
@ContentRowHeight(18)
public class ExcelDataDto {

    /**
     * 这里不建议 index 和 name 同时用，要么一个对象只用index，要么一个对象只用name去匹配
     */
    @ExcelProperty(index = 0)
    private Integer xuhao;
    /**
     * 用名字去匹配，这里需要注意，如果名字重复，会导致只有一个字段读取到数据
     */
    @ExcelProperty("IBU")
    @ColumnWidth(15)
    private String ibu;


    /**
     * DateTimeFormat 为导出时的数据格式
     */
    @ExcelProperty("更新指标时间")
    @DateTimeFormat("yyyy年MM月dd日HH时mm分ss秒")
    private Date date;

    /**
     * converter:为自定义转化器，在读或者写的时候会被调用
     * ContentFontStyle：内容字体设置为20
     * ContentStyle：内容设置背景色为天蓝
     */
    @ExcelProperty(value = "是否评估",converter = NumCharConverter.class)
    @ContentFontStyle(fontHeightInPoints = 10)
    @ContentStyle(fillPatternType = FillPatternType.SOLID_FOREGROUND, fillForegroundColor = 40)
    private String isBuy;

    /**
     *  字符串的头背景设置成粉红，使用IndexedColors.PINK.getIndex()，查看
     */
    @HeadStyle(fillPatternType = FillPatternType.SOLID_FOREGROUND, fillForegroundColor = 14)
    @ExcelProperty(value = "原车牌号")
    private String licence;

    /**
     * 忽略这个字段
     */
    @ExcelIgnore
    private String sdf;



}
