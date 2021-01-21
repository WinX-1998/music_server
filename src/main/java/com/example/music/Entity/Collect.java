package com.example.music.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Jiangweixin
 * @Date: 2021/01/20/14:32
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Collect implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;
    @TableField(value = "user_id")
    private Integer userId;
    private Integer type;
    @TableField(value = "song_id")
    private Integer songId;
    @TableField(value = "song_list_id")
    private Integer songListId;
    @TableField(value = "create_time")
    private Date createTime;
}
