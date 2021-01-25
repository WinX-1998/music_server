package com.example.music.Entity;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Jiangweixin
 * @Date: 2021/01/24/21:39
 * @Description:
 */
public enum LoginType {
    UESR("User"), ADMIN("Admin");

    private String type;
    private LoginType(String type){
        this.type=type;
    }

    @Override
    public String toString(){
        return this.type.toString();
    }
}
