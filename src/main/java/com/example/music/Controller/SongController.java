package com.example.music.Controller;

import com.example.music.Service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Jiangweixin
 * @Date: 2020/12/30/16:21
 * @Description:
 */
@RestController
@RequestMapping("/song")
public class SongController {
    @Autowired
    private SongService songService;


}
