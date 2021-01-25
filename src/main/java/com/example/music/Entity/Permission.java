package com.example.music.Entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Jiangweixin
 * @Date: 2021/01/24/20:03
 * @Description:
 */
@Data
@TableName("permission")
public class Permission implements Serializable {
    private Integer id;
    private String name;
    private String code;
    private String url;
//    private String permType;//0:资源菜单 1资源下的权限
}
