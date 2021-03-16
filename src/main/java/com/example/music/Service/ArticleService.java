package com.example.music.Service;

import com.example.music.Entity.Article;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Jiangweixin
 * @Date: 2021/02/08/22:14
 * @Description:
 */
public interface ArticleService {
    public boolean insert(Article article);
    public List<Article> selectAll();
    public Article selectArticleById(Integer id);
    public Article selectArticleHtmlById(Integer id);
    public boolean update(Article article);
    public boolean deleteById(Integer id);
    public Article selectArticleByCreateTime(Date date);
    public boolean deleteByIds(List<Integer>ids);
}
