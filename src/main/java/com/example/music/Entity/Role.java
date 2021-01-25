package com.example.music.Entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Jiangweixin
 * @Date: 2021/01/24/20:03
 * @Description:
 */
@Data
@TableName("role")
public class Role {
    private Integer id;
    private String name;
    private String code;
    private String intro;

    @TableField(exist = false)
    private List<Permission> permissionList;
}
