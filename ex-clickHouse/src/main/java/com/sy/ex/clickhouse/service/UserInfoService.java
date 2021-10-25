package com.sy.ex.clickhouse.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sy.ex.clickhouse.domain.UserInfo;

/**
 * @Author: sy
 * @Date: Created by 2021/10/25 14:12
 * @description:
 */
public interface UserInfoService extends IService<UserInfo> {
    void createTable();

}
