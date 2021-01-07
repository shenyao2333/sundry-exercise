package com.sy.ex.springboot.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

/**
 * @Author: shenyao
 * @Date: Created by 2021/1/7 21:28
 * @description:
 */
@Slf4j
@Configuration
@ConfigurationProperties(prefix = "spring.test")
@Data
public class ConfigTest {

    ArrayList<String> userName  = new ArrayList<String>();


}