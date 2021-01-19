package com.example.music.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Jiangweixin
 * @Date: 2021/01/18/21:46
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Grade implements Serializable {
    /*主键*/
    @TableId(type = IdType.AUTO)
    private Integer id;
    /*歌单*/
    @TableField(value = "song_list_id")
    private Integer songListId;
    /*用户*/
    @TableField(value = "user_id")
    private Integer userId;
    /*评分*/
    private Integer score;
}
