package com.example.music.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.music.Entity.Comment;
import com.example.music.Mapper.CommentMapper;
import com.example.music.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Jiangweixin
 * @Date: 2021/01/19/16:28
 * @Description:
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;


    @Override
    public boolean insert(Comment comment) {
        int insert = commentMapper.insert(comment);
        if(insert!=0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean update(Comment comment) {
        int update = commentMapper.updateById(comment);
        if(update!=0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean delete(Integer id) {
        int delete = commentMapper.deleteById(id);
        if(delete!=0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Comment selectByPrimaryKey(Integer id) {
        Comment comment = commentMapper.selectById(id);
        return comment;
    }

    @Override
    public List<Comment> allComment() {
        List<Comment> comments = commentMapper.selectList(null);
        return comments;
    }

    @Override
    public List<Comment> commentOfSongId(Integer songId) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("song_id",songId);
        List list = commentMapper.selectList(queryWrapper);
        return list;
    }

    @Override
    public List<Comment> commentOfSongListId(Integer songListId) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("song_list_id",songListId);
        List list = commentMapper.selectList(queryWrapper);
        return list;
    }
}
