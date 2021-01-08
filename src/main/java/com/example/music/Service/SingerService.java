package com.example.music.Service;

import com.example.music.Entity.Singer;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Jiangweixin
 * @Date: 2020/12/30/14:12
 * @Description:
 */
public interface SingerService {

    /**
     *增加
     */
    public int insert(Singer singer);

    /**
     *修改
     */
    public int update(Singer singer);

    /**
     * 删除
     */
    public int delete(Integer id);

    /**
     * 根据主键查询整个对象
     */
    public Singer selectById(Integer id);

    /**
     * 批量删除歌手
     */
    public int delectByIds(List<Integer> ids);

    /**
     * 查询所有歌手
     */
    public List<Singer> selectAllSinger();

    /**
     * 根据歌手名字模糊查询列表
     */
    public List<Singer> selectByLikeName(String name);


    /**
     * 根据性别查询
     */
    public List<Singer> selectBySex(Integer sex);
}
