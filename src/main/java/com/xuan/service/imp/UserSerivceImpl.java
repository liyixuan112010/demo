package com.xuan.service.imp;

import com.xuan.dao.UserMapper;
import com.xuan.pojo.User;
import com.xuan.service.IUserService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("userService")
public class UserSerivceImpl implements IUserService {

    @Resource
    private UserMapper userMapper;

    @Cacheable("user")
    @Override
    public List<User> userAll() {
        return userMapper.getUserAll();
    }

    @Override
    public User getUserByAccount(String account) {
        return userMapper.getUserByAccount(account);
    }
}
