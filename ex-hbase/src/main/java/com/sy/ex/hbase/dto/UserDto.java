package com.sy.ex.hbase.dto;

import com.sy.ex.hbase.domain.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: sy
 * @Date: Created by 2021/9/29 16:11
 * @description:
 */
@Mapper
public interface UserDto {


    List<UserInfo> queryAll();

    void save(UserInfo userInfo);

    void saveList(@Param("userInfos") List<UserInfo> userInfos);

    List<UserInfo> getByParam(UserInfo userInfo);

    void deleteByName(String name);

    void createTable();

    List<UserInfo> executeSql(@Param("sql") String sql);
}
