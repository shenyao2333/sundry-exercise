package com.sy.ex.sharding.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sy.ex.sharding.domain.UserInfo;

import java.util.List;

/**
 * @Author: sy
 * @Date: Created by 2021/6/18 10:27
 * @description:
 */

public interface UserInfoMapper  extends BaseMapper<UserInfo> {


    List<Object> getByAge(Integer age);

    void del();


    List<Object> getByAll(UserInfo user);

}
