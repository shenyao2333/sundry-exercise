package com.sy.sunder.ex.easyexcel.service;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;

/**
 * @Author: shenyao
 * @Date: Created by 2020/11/20 20:16
 * @description: 数字与中文互转
 */
public class NumCharConverter  implements Converter<String> {


    @Override
    public Class supportJavaTypeKey() {
        return String.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }


    /**
     * 读的时候调用
     * @param cellData
     * @param excelContentProperty
     * @param globalConfiguration
     * @return
     * @throws Exception
     */
    @Override
    public String convertToJavaData(CellData cellData, ExcelContentProperty excelContentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        String value = cellData.getStringValue();
        if (value!=null){
            if ("是".equals(value)){
                return "1";
            }else if ("否".equals(value)){
                return "0";
            }
        }
        return value;
    }

    /**
     * 写的时候调用
     * @param
     * @param excelContentProperty
     * @param globalConfiguration
     * @return
     * @throws Exception
     */
    @Override
    public CellData convertToExcelData(String value, ExcelContentProperty excelContentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        if (value!=null){
            if ("1".equals(value)){
                value =  "是";
            }else if ("0".equals(value)){
                value= "否";
            }
        }
        return new CellData(value);
    }
}
