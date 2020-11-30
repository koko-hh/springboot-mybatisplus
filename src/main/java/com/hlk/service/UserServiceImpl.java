package com.hlk.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hlk.mapper.UserMapper;
import com.hlk.pojo.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{
}
