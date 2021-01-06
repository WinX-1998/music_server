package com.example.music.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Jiangweixin
 * @Date: 2021/01/05/18:00
 * @Description:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SongList implements Serializable {
        /*主键*/
        @TableId(type = IdType.AUTO)
        private Integer id;
        /*歌单标题*/
        private String title;
        /*歌单图片*/
        private String pic;
        /*简介*/
        private String introduction;
        /*风格*/
        private String style;
}
