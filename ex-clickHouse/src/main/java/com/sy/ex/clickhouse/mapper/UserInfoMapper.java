package com.sy.ex.clickhouse.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sy.ex.clickhouse.domain.UserInfo;


public interface UserInfoMapper extends BaseMapper<UserInfo> {

    void createTable();

}
