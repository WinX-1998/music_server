package com.example.music.Service.Impl;

import com.example.music.Entity.Tag;
import com.example.music.Mapper.TagMapper;
import com.example.music.Service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Jiangweixin
 * @Date: 2021/02/15/0:33
 * @Description:
 */
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagMapper tagMapper;


    @Override
    public boolean insert(Tag tag) {
        int insert = tagMapper.insert(tag);
        if(insert!=0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean update(Tag tag) {
        int i = tagMapper.updateById(tag);
        if(i!=0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public List<Tag> selectAll() {
        List<Tag> tags = tagMapper.selectList(null);
        return tags;
    }

    @Override
    public Tag selectById(Integer id) {
        Tag tag = tagMapper.selectById(id);
        return tag;
    }

    @Override
    public boolean deleteById(Integer id) {
        int delete = tagMapper.deleteById(id);
        if (delete != 0) {
            return true;
        } else {
            return false;
        }
    }
}
