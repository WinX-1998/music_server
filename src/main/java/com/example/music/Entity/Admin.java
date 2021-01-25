package com.example.music.Entity;


import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admin {

    private Integer id;
    @TableField(value="admin_name")
    private String adminName;
    private String realname;
    private String password;
    private String salt;
}
