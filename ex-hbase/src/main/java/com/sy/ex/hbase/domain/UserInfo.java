package com.sy.ex.hbase.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: sy
 * @Date: Created by 2021/9/29 16:09
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {


    private Integer userId;

    private String name;

    private String phone;

    private Integer age;

    private String sex;

}
