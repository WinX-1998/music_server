package com.example.music.Controller;

import com.example.music.Entity.Article;
import com.example.music.Entity.Singer;
import com.example.music.Entity.Tag;
import com.example.music.Mapper.ArticleMapper;
import com.example.music.Mapper.UtilsMapper;
import com.example.music.Service.ArticleService;
import com.example.music.Service.TagService;
import com.example.music.VO.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Jiangweixin
 * @Date: 2021/02/08/22:20
 * @Description:
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    ArticleService articleService;
    @Autowired
    UtilsMapper utilsMapper;

    @GetMapping("/selectAll")
    public List<Article> selectAll() {
        List<Article> articles = articleService.selectAll();
        List<Article>list=new ArrayList<>();
        for(Article article:articles){
            List<Tag> tags = utilsMapper.selectByArticleId(article.getId());
            article.setTags(tags);
            list.add(article);
        }
        return list;
    }

    @GetMapping("/selectArticleById/{id}")
    public Article selectArticleById(@PathVariable Integer id) {
        Article article = articleService.selectArticleById(id);
        List<Tag> tags = utilsMapper.selectByArticleId(article.getId());
        article.setTags(tags);
        return article;
    }

    @GetMapping("/selectArticleHtmlById/{id}")
    public Article selectArticleHtmlById(@PathVariable Integer id) {
        Article article = articleService.selectArticleHtmlById(id);
        Article article1 = articleService.selectArticleById(id);
        article1.setArticleView(article.getArticleView()+1);
        articleService.update(article1);
        List<Tag> tags = utilsMapper.selectByArticleId(article.getId());
        article.setTags(tags);
        return article;
    }

    @PostMapping("/insert")
    public Response insert(HttpServletRequest request) {
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String content = request.getParameter("content");
        String firstPic = request.getParameter("firstPic");
        String tagIds = request.getParameter("tagIds");
        Article article=new Article();
        article.setTitle(title);
        article.setAuthor(author);
        article.setContent(content);
        article.setFirstPic(firstPic);
        article.setArticleView(0);
        article.setArticleLike(0);
        Date date=new Date();
        article.setCreateTime(date);
        article.setUpdateTime(date);
        boolean flag = articleService.insert(article);
        if(flag){
            Article article1 = articleService.selectArticleByCreateTime(date);
            Integer articleId = article1.getId();
            String[] split = tagIds.split(",");
            for(int i=0;i<split.length;i++){
                String num=split[i];
                utilsMapper.insert(articleId, Integer.parseInt(num));
            }
            return new Response(200,"新增成功",null);
        }else{
            return new Response(500,"新增失败",null);
        }
    }

    @PostMapping("/update")
    public Response update(HttpServletRequest request) {
        String id = request.getParameter("id");
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String content = request.getParameter("content");
        String firstPic = request.getParameter("firstPic");
        String tagIds = request.getParameter("tagIds");
        Article article = new Article();
        article.setId(Integer.parseInt(id));
        article.setTitle(title);
        article.setAuthor(author);
        article.setContent(content);
        article.setFirstPic(firstPic);
        article.setArticleView(0);
        article.setArticleLike(0);
        Date date = new Date();
        article.setUpdateTime(date);
        Article articleQuery = articleService.selectArticleById(Integer.parseInt(id));
        BeanUtils.copyProperties(article, articleQuery);
        boolean flag = articleService.update(articleQuery);
        if (flag) {
            if (utilsMapper.selectByArticleId(Integer.parseInt(id)).size() != 0) {
                utilsMapper.deleteByArticleId(Integer.parseInt(id));
            }
            String[] split = tagIds.split(",");
            for (int i = 0; i < split.length; i++) {
                String num = split[i];
                utilsMapper.insert(Integer.parseInt(id), Integer.parseInt(num));
            }
            return new Response(200, "更新成功", null);
        }
        return new Response(500, "更新失败", null);
    }

    @DeleteMapping("/delete/{id}")
    public Response delete(@PathVariable Integer id) {
        boolean flag = articleService.deleteById(id);
        if (flag) {
            return new Response(200, "删除成功", null);
        } else {
            return new Response(500, "删除失败", null);
        }
    }

    @PostMapping("/deleteNewFirstPic")
    public Response deleteNewFirstPic(HttpServletRequest request){
        String newFirstPic = request.getParameter("newFirstPic");
        String subUrl = newFirstPic.substring(21);
        String oldFilePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "img"
                + System.getProperty("file.separator") + "articleFirstPic" + System.getProperty("file.separator") + subUrl;
        File file1 = new File(oldFilePath);
        boolean delete = file1.delete();
        if(delete){
            return new Response(200, "图片删除成功", null);
        }else{
            return new Response(500, "图片删除失败", null);
        }
    }


    /**
     * 更新首图
     */
    @PostMapping(value = "/updateFirstPic")
    public Response updateFirstPic(@RequestParam("file") MultipartFile avatorFile,@RequestParam("id")Integer id) {
        if (avatorFile.isEmpty()) {
            return new Response(500, "文件上传失败", null);
        }
        //文件名=当前时间到毫秒+原来的文件名
        String fileName = System.currentTimeMillis() + avatorFile.getOriginalFilename();
        //文件路径
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "img"
                + System.getProperty("file.separator") + "articleFirstPic";
        //如果文件路径不存在，新增该路径
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdir();
        }
        //实际的文件地址
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        //存储到数据库里相对的文件地址
        String storeAvatorPath = "/img/articleFirstPic/" + fileName;
        if (id != 0) {
            Article article = articleService.selectArticleById(id);
            String firstPic = article.getFirstPic();
            if (firstPic != null) {
                String subUrl = firstPic.substring(14);
                String oldFilePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "img"
                        + System.getProperty("file.separator") + "articleFirstPic" + System.getProperty("file.separator") + subUrl;
                File file1 = new File(oldFilePath);
                file1.delete();
                System.out.println("删除了"+subUrl);
            }
            try {
                avatorFile.transferTo(dest);
                article.setFirstPic(storeAvatorPath);
                boolean flag = articleService.update(article);
                if (flag) {
                    return new Response(200, "文件上传成功", storeAvatorPath);
                } else {
                    return new Response(500, "文件上传失败", null);
                }
            } catch (IOException e) {
                return new Response(500, "文件上传失败" + e.getMessage(), null);
            }
        } else {
            try {
                avatorFile.transferTo(dest);
                return new Response(200, "文件上传成功", storeAvatorPath);
            } catch (IOException e) {
                return new Response(500, "文件上传失败" + e.getMessage(), null);
            }
        }
    }

    @GetMapping("/likeArticle/{id}")
    public Response likeArticle(@PathVariable("id")Integer id){
        Article article = articleService.selectArticleById(id);
        article.setArticleLike(article.getArticleLike()+1);
        boolean flag = articleService.update(article);
        if(flag){
            return new Response(200,"点赞成功",null);
        }else{
            return new Response(500,"点赞失败",null);
        }
    }
}
