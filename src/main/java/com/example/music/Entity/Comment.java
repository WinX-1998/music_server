package com.example.music.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Jiangweixin
 * @Date: 2021/01/19/16:23
 * @Description:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment implements Serializable {
    /*主键*/
    @TableId(type = IdType.AUTO)
    private Integer id;
    /*用户id*/
    @TableField(value = "user_id")
    private Integer userId;
    /*评论类型（0歌曲1歌单）*/
    private Integer type;
    /*歌曲id*/
    @TableField(value = "song_id")
    private Integer songId;
    /*歌单id*/
    @TableField(value = "song_list_id")
    private Integer songListId;
    /*评论内容*/
    private String content;
    /*评论时间*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = "create_time")
    private Date createTime;
    /*评论点赞数*/
    private Integer up;


}
