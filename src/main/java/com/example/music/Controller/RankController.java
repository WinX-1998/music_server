package com.example.music.Controller;

import com.example.music.Entity.Rank;
import com.example.music.Service.RankService;
import com.example.music.VO.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Jiangweixin
 * @Date: 2021/01/18/22:17
 * @Description:
 */
@RestController
@RequestMapping("/rank")
public class RankController {

    @Autowired
    private RankService rankService;


    @PostMapping("/addRank")
    public Response addRank(@RequestBody Rank rank){
        boolean flag = rankService.insert(rank);
        if(flag){
            return new Response(200,"评价成功",null);
        }else{
            return new Response(500,"评价失败",null);
        }
    }

    @GetMapping("/rankOfSongListId/{id}")
    public int rankOfSongListId(@PathVariable("id")String id){
        if(!id.isEmpty()) {
            return rankService.rankOfSongListId(Integer.parseInt(id));
        }else{
            return 0;
        }
        }



}
