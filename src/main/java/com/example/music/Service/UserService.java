package com.example.music.Service;

import com.example.music.Entity.User;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Jiangweixin
 * @Date: 2021/01/08/15:52
 * @Description:
 */
public interface UserService {
    /**
     *增加
     */
    public boolean insert(User user);

    /**
     *修改
     */
    public boolean update(User user);

    /**
     * 删除
     */
    public boolean delete(Integer id);

    /**
     * 删除
     */
    public boolean deleteUsers(List<Integer> ids);


    /**
     * 根据主键查询整个对象
     */
    public User selectByPrimaryKey(Integer id);


    /**
     * 查询一年内各个月份的新增人数
     */
    public Map<Integer,Integer> selectUserCreatTimeOnYear(int year);

    /**
     * 查询所有用户
     */
    public List<User> selectAllUser();

    /**
     * 查看密码是否正确
     */
    public boolean verifyPassword(String username,String password);

    /**
     * 根据账号查询
     */
    public User selectByUserName(String username);
}
