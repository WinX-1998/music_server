package com.example.music.VO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Jiangweixin
 * @Date: 2021/01/22/21:53
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentInfo {
    private Integer userId;
    private String username;
    private Integer commentId;
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    private Integer up;
}
