package com.sy.ex.sharding.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sy.ex.sharding.domain.UserInfo;
import com.sy.ex.sharding.mapper.UserInfoMapper;
import com.sy.ex.sharding.service.UserInfoService;
import org.springframework.stereotype.Service;

/**
 * @Author: sy
 * @Date: Created by 2021/6/18 10:26
 * @description:
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {
}
