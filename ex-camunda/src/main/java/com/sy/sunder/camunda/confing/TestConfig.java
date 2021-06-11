package com.sy.sunder.camunda.confing;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.camunda.spin.impl.json.jackson.format.JacksonJsonDataFormat;
import org.camunda.spin.spi.DataFormat;
import org.camunda.spin.spi.DataFormatConfigurator;
import org.springframework.boot.autoconfigure.jackson.JacksonProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: sy
 * @Date: Created by 2021/6/11 10:22
 * @description:
 */
@Component
public class TestConfig implements DataFormatConfigurator<JacksonJsonDataFormat> {


    @Override
    public Class<JacksonJsonDataFormat> getDataFormatClass() {
        return null;
    }

    @Override
    public void configure(JacksonJsonDataFormat jacksonJsonDataFormat) {
        System.out.println("-----");
    }
}
