package com.example.music.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Transient;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Jiangweixin
 * @Date: 2021/02/08/22:04
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String title;
    private String author;
    private String content;
    @TableField(value="first_pic")
    private String firstPic;
    @TableField(value="article_view")
    private Integer articleView;
    @TableField(value="article_like")
    private Integer articleLike;
    @TableField(exist = false)
    private List<Tag>tags;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value="create_time")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value="update_time")
    private Date updateTime;
}
