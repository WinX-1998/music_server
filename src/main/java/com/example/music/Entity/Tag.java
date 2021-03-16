package com.example.music.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Jiangweixin
 * @Date: 2021/02/08/22:11
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tag {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
}
