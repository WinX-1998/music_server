package com.example.music;

import com.example.music.Entity.LoginType;
import com.example.music.Entity.Singer;
import com.example.music.Entity.Tag;
import com.example.music.Entity.User;
import com.example.music.Mapper.SingerMapper;
import com.example.music.Mapper.UtilsMapper;
import com.example.music.Service.SingerService;
import com.example.music.Service.UserService;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SpringBootTest
class MusicApplicationTests {
    @Autowired
    SingerService singerService;
    @Autowired
    UserService userService;

    @Autowired
    UtilsMapper utilsMapper;

    @Test
    void contextLoads() {
        String password = "123456";
        String salt=new SecureRandomNumberGenerator().nextBytes().toString();
        System.out.println(salt);
        int times=2;
        String algorithm="md5";
        String pwdAfterHash=new SimpleHash(algorithm,password,salt,times).toString();
        System.out.println(pwdAfterHash);
        //tgp2nDn5CscNabBgIo4txQ==
        //64825577f0f0f298c843d06b300affb7
    }

    @Test
    void test(){
        boolean insert = utilsMapper.insert(13, 14);
        System.out.println(insert);
    }

}
