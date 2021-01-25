package com.example.music.Service;

import com.example.music.Entity.Comment;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Jiangweixin
 * @Date: 2021/01/19/16:27
 * @Description:
 */

/**
 * 评论service接口
 */
public interface CommentService {
    /**
     *增加
     */
    public boolean insert(Comment comment);

    /**
     *修改
     */
    public boolean update(Comment comment);

    /**
     * 删除
     */
    public boolean delete(Integer id);

    /**
     * 根据主键查询整个对象
     */
    public Comment selectByPrimaryKey(Integer id);

    /**
     * 根据主键查询整个对象
     */
    public  List<Comment> selectBySongId(Integer songId);

    /**
     * 根据歌单id查询评论
     */
    public  List<Comment> selectBySongListId(Integer songListId);


    /**
     * 批量删除评论
     */
    public boolean deleteComments(List<Integer>ids);

    /**
     * 查询所有评论
     */
    public List<Comment> allComment();

    /**
     * 查询某个歌曲下的所有评论
     */
    public List<Comment> commentOfSongId(Integer songId);

    /**
     * 查询某个歌单下的所有评论
     */
    public List<Comment> commentOfSongListId(Integer songListId);
}
