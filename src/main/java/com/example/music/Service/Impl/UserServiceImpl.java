package com.example.music.Service.Impl;

import com.example.music.Entity.User;
import com.example.music.Mapper.UserMapper;
import com.example.music.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findUserByUsername(String username) {
       return userMapper.findByUsername(username);
    }

    @Override
    public User findUser(String username,String password) {


        return null;
    }
}
