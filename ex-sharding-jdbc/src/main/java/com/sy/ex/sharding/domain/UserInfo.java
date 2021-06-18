package com.sy.ex.sharding.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author: sy
 * @Date: Created by 2021/6/18 10:20
 * @description:
 */
@Data
@TableName("user_info")
public class UserInfo {

    @TableId(value = "id")
    private Long id;

    @TableField("user_name")
    private String userName;

    @TableField("age")
    private Integer age;

    @TableField("phone")
    private String phone;



}
