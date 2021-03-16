package com.example.music.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.music.Entity.Tag;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Jiangweixin
 * @Date: 2021/02/17/21:48
 * @Description:
 */
@Mapper
public interface UtilsMapper extends BaseMapper {
    @Insert("insert into article_tag(article_id,tag_id) values(#{articleId},#{tagId})")
    public boolean insert(Integer articleId,Integer tagId);

    @Select("SELECT t.* FROM article_tag atg JOIN article a ON a.`id` = atg.`article_id` JOIN tag t ON atg.`tag_id` = t.`id`WHERE a.`id` = #{articleId} ")
    public List<Tag> selectByArticleId(Integer articleId);

    @Delete("DELETE atg.* FROM article_tag atg JOIN article a ON a.`id` = atg.`article_id` JOIN tag t ON atg.`tag_id` = t.`id`WHERE a.`id` = #{articleId}")
    public boolean deleteByArticleId(Integer articleId);

}
