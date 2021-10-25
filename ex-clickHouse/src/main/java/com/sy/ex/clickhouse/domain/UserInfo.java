package com.sy.ex.clickhouse.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author: sy
 * @Date: Created by 2021/9/29 16:09
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {

    /**
     * 主键id
     */
    private Integer userId;

    /**
     * 姓名
     */
    private String name;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 性别 0-女，1-男，2-未知
     */
    private Integer sex;

    /**
     * 头像
     */
    private String icon;

    /**
     * openid
     */
    private String openid;

    /**
     * 创建时间
     */
    private Date createTime;


}
