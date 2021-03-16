package com.example.music.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.music.Entity.Article;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Jiangweixin
 * @Date: 2021/02/08/22:13
 * @Description:
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {
}
