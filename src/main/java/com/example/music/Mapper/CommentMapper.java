package com.example.music.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.music.Entity.Comment;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Jiangweixin
 * @Date: 2021/01/19/16:26
 * @Description:
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {
}
