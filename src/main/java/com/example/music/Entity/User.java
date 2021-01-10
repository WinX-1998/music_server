package com.example.music.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Jiangweixin
 * @Date: 2021/01/08/15:43
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    /*主键*/
    @TableId(type = IdType.AUTO)
    private Integer id;
    /*账号*/
    private String username;
    /*密码*/
    private String password;
    /*性别*/
    private Integer sex;
    /*手机号*/
    private String phoneNum;
    /*电子邮箱*/
    private String email;
    /*生日*/
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date birth;
    /*签名*/
    private String introduction;
    /*地区*/
    private String location;
    /*头像*/
    private String avator;
    /*创建时间*/
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;
    /*更新时间*/
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date updateTime;
}
