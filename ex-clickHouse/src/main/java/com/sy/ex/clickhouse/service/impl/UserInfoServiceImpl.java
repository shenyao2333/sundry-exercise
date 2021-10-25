package com.sy.ex.clickhouse.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sy.ex.clickhouse.domain.UserInfo;
import com.sy.ex.clickhouse.mapper.UserInfoMapper;
import com.sy.ex.clickhouse.service.UserInfoService;
import org.springframework.stereotype.Service;

/**
 * @Author: sy
 * @Date: Created by 2021/10/25 14:13
 * @description:
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {



    @Override
    public void createTable() {
        baseMapper.createTable();
    }
}
