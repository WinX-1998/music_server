package com.example.music.Entity;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Jiangweixin
 * @Date: 2021/01/24/21:39
 * @Description:
 */
public enum LoginType {
    USER("User"), ADMIN("Admin");

    private String type;
    LoginType(String type){
        this.type=type;
    }


    public String getType() {
        return type;
    }
}
