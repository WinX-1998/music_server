package com.example.music.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.music.Entity.Article;
import com.example.music.Mapper.ArticleMapper;
import com.example.music.Service.ArticleService;
import com.example.music.utils.MarkdownUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Jiangweixin
 * @Date: 2021/02/08/22:17
 * @Description:
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;
    @Override
    public boolean insert(Article article) {
        int insert = articleMapper.insert(article);
        if(insert!=0){
            return true;
        }else{
            return false;
        }

    }

    @Override
    public List<Article> selectAll() {
        List<Article> articles = articleMapper.selectList(null);
        return articles;
    }

    @Override
    public Article selectArticleById(Integer id) {
        Article article = articleMapper.selectById(id);
        return article;
    }

    @Override
    public Article selectArticleHtmlById(Integer id) {
        Article article = articleMapper.selectById(id);
        article.setContent(MarkdownUtils.markdownToHtmlExtensions(article.getContent()));
        return article;
    }

    @Override
    public boolean update(Article article) {
        int i = articleMapper.updateById(article);
        if(i!=0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean deleteById(Integer id) {
        int delete = articleMapper.deleteById(id);
        if(delete!=0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Article selectArticleByCreateTime(Date date) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("create_time",date);
        Article article = articleMapper.selectOne(queryWrapper);
        return article;
    }

    @Override
    public boolean deleteByIds(List<Integer> ids) {
        int i = articleMapper.deleteBatchIds(ids);
        if(i!=0){
            return true;
        }else {
            return false;
        }
    }

}
