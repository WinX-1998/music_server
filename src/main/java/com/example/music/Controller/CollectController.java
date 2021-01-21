package com.example.music.Controller;

import com.example.music.Entity.Collect;
import com.example.music.Service.CollectService;
import com.example.music.VO.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Jiangweixin
 * @Date: 2021/01/20/14:44
 * @Description:
 */
@RestController
@RequestMapping("/collect")
public class CollectController {

    @Autowired
    private CollectService collectService;

    @PostMapping("/addCollect")
    public Response addCollect(HttpServletRequest request){
        String userId = request.getParameter("userId");
        String songId = request.getParameter("songId");
        String songListId = request.getParameter("songListId");
        String type = request.getParameter("type");
        if(Integer.parseInt(type)==0){
            if(songId==null||songId.equals("")){
                return new Response(500,"歌曲不存在",null);
            }
            if(collectService.existSongId(Integer.parseInt(userId),Integer.parseInt(songId))){
                return new Response(500,"歌曲已收藏",null);
            }
            Collect collect=new Collect();
            collect.setSongId(Integer.parseInt(songId));
            collect.setType(0);
            collect.setCreateTime(new Date());
            collect.setUserId(Integer.parseInt(userId));
            boolean insert = collectService.insert(collect);
            if(insert){
                return new Response(200,"收藏成功",null);
            }else{
                return new Response(500,"收藏失败",null);
            }
        }else{
            if(songListId==null||songListId.equals("")){
                return new Response(500,"歌单不存在",null);
            }
            if(collectService.existSongListId(Integer.parseInt(userId),Integer.parseInt(songListId))){
                return new Response(500,"歌单已收藏",null);
            }
            Collect collect=new Collect();
            collect.setSongListId(Integer.parseInt(songListId));
            collect.setType(0);
            collect.setCreateTime(new Date());
            collect.setUserId(Integer.parseInt(userId));
            boolean insert = collectService.insert(collect);
            if(insert){
                return new Response(200,"收藏成功",null);
            }else{
                return new Response(500,"收藏失败",null);
            }
        }
    }

    /**
     * 删除收藏
     */
    @DeleteMapping("/delete")
    public Response deleteCollect(HttpServletRequest request){
        String type=request.getParameter("type");
        String userId = request.getParameter("userId");
        if(Integer.parseInt(type)==0){
        String songId = request.getParameter("songId");
        boolean flag = collectService.deleteByUserIdSongId(Integer.parseInt(userId),Integer.parseInt(songId));
        if(flag){
            return new Response(200,"删除成功",null);
        }else{
            return new Response(500,"删除失败",null);
            }
        }else{
            String songListId = request.getParameter("songListId");
            boolean flag = collectService.deleteByUserIdSongListId(Integer.parseInt(userId),Integer.parseInt(songListId));
            if(flag){
                return new Response(200,"删除成功",null);
            }else{
                return new Response(500,"删除失败",null);
            }
        }
    }

    /**
     * 查询所有收藏
     */
    @GetMapping("/selecyAllCollect")
    public List<Collect> selecyAllCollect(HttpServletRequest request){
        List<Collect> collects = collectService.selectAllCollet();
        return collects;
    }

    /**
     * 查询某个用户的收藏列表
     */
   @GetMapping("/selectCollectByUserId/{userId}")
    public List<Collect> collectOfUserId(@PathVariable("userId")String userId){
        return collectService.collectOfUserId(Integer.parseInt(userId));
    }

    @PostMapping("/isCollected")
    public Response isCollected(HttpServletRequest request){
        String userId = request.getParameter("userId");
        String songId = request.getParameter("songId");
        boolean flag = collectService.existSongId(Integer.parseInt(userId), Integer.parseInt(songId));
        if(flag){
            return new Response(200,"已经收藏",null);
        }else{
            return new Response(500,"未收藏",null);
        }
    }
}
