package com.xuan.dao;

import com.xuan.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface UserMapper {
    public List<User> getUserAll();

    public User getUserByAccount(String account);
}
