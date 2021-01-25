package com.example.music.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.music.Entity.User;
import com.example.music.Mapper.UserMapper;
import com.example.music.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Jiangweixin
 * @Date: 2021/01/08/15:53
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean insert(User user) {
        int insert = userMapper.insert(user);
        if(insert!=0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean update(User user) {
        int i = userMapper.updateById(user);
        if(i!=0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean delete(Integer id) {
        int i = userMapper.deleteById(id);
        if(i!=0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean deleteUsers(List<Integer> ids) {
        int i = userMapper.deleteBatchIds(ids);
        if(i!=0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean changeIsVip(Integer id, Boolean isVip) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("id",id);
        User user = userMapper.selectById(id);
        user.setIsVip(isVip);
        int i = userMapper.updateById(user);
        if(i!=0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public User selectByPrimaryKey(Integer id) {
        return userMapper.selectById(id);
    }

    @Override
    public List<User> selectAllUser() {
        List<User> users = userMapper.selectList(null);
        return users;
    }

    @Override
    public Map<Integer,Integer> selectUserCreatTimeOnYear(int year){
        List<User> users = userMapper.selectList(null);
        List<User>queryUsers=new ArrayList<User>();
        for(User user:users){
            if((user.getCreateTime().getYear()+1900)==year){
                queryUsers.add(user);
            }
        }
        Map<Integer,Integer>map=new HashMap<>();
        for(int i=0;i<12;i++){
            map.put(i+1,0);
        }
        for(User user:queryUsers){
            int month=user.getCreateTime().getMonth()+1;
            if(!map.containsKey(month)){
                map.put(month,1);
            }else{
                Integer integer = map.get(month);
                integer++;
                map.put(month,integer);
            }
        }
        return map;
    }

    @Override
    public boolean verifyPassword(String username, String password) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("username",username);
        User user = userMapper.selectOne(queryWrapper);
        if(user.getPassword().equals(password)){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public User selectByUserName(String username) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("username",username);
        User user = userMapper.selectOne(queryWrapper);
        return user;
    }
}
