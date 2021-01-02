package com.example.music;

import com.example.music.Entity.Singer;
import com.example.music.Service.SingerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootTest
class MusicApplicationTests {
    @Autowired
    SingerService singerService;
    @Test
    void contextLoads() {
        System.out.println(singerService.selectAllSinger());
    }

}
