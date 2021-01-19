package com.example.music.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.music.Entity.Grade;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Jiangweixin
 * @Date: 2021/01/18/21:48
 * @Description:
 */
@Mapper
public interface GradeMapper extends BaseMapper<Grade> {

    @Select("select COALESCE(sum(score),0) as score from grade where song_list_id=#{songListId}")
    public int selectScoreSum(@Param("songListId") Integer songListId);
}
