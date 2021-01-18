package com.example.music.Entity;

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
public class Rank implements Serializable {
    /*主键*/
    private Integer id;
    /*歌单*/
    private Integer songListId;
    /*用户*/
    private Integer userId;
    /*评分*/
    private Integer score;
}
