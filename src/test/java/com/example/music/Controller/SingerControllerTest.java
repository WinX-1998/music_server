package com.example.music.Controller;

import com.example.music.Entity.Singer;
import com.example.music.Service.SingerService;
import com.example.music.VO.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Jiangweixin
 * @Date: 2020/12/30/14:49
 * @Description:
 */
class SingerControllerTest {
    @Autowired
    SingerService singerService;
    @Test
    void addSinger() {
        Singer singer = new Singer();
        singer.setName("zhangsan");
        singer.setSex(1);
        singer.setIntroduction("...");
        int i = singerService.insert(singer);
        System.out.println(i);

    }
}