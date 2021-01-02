package com.example.music.Service;


import com.example.music.Entity.User;

public interface UserService {
    public User findUserByUsername(String username);
    public User findUser(String username,String password);
}
