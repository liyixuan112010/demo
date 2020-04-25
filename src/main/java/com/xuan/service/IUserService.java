package com.xuan.service;

import com.xuan.pojo.User;

import java.util.List;

public interface IUserService {
    public List<User> userAll();

    public User getUserByAccount(String account);
}
