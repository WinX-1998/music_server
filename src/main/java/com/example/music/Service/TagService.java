package com.example.music.Service;

import com.example.music.Entity.Tag;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Jiangweixin
 * @Date: 2021/02/15/0:31
 * @Description:
 */
public interface TagService {
    public boolean insert(Tag tag);
    public boolean update(Tag tag);
    public List<Tag> selectAll();
    public Tag selectById(Integer id);
    public boolean deleteById(Integer id);
}
