package com.sy.ex.springboot.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @Author: shenyao
 * @Date: Created by 2021/1/8 21:46
 * @description:
 */
@Data
public class DataSource {
    private Integer url;
    private String username;
}
