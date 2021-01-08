package com.example.music.Controller;

import com.example.music.Entity.ListSong;
import com.example.music.Entity.Song;
import com.example.music.Entity.SongList;
import com.example.music.Service.ListSongService;
import com.example.music.Service.SongListService;
import com.example.music.Service.SongService;
import com.example.music.VO.Response;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Jiangweixin
 * @Date: 2021/01/07/14:10
 * @Description:
 */
@RestController
@RequestMapping("/listSong")
public class ListSongController {

    @Autowired
    private ListSongService listSongService;
    @Autowired
    private SongService songService;
    @Autowired
    private SongListService songListService;

    @GetMapping("/selectSongsBySongList/{id}")
    public List<ListSong> selectSongsBySongList(@PathVariable("id")String SongListid)
    {
        if(SongListid!=null){
            List<ListSong> listSongs = listSongService.listSongOfSongListId(Integer.parseInt(SongListid));
            return listSongs;
        }else {
            return null;
        }
    }

    @PostMapping("/addListSong")
    public Response addListSong(HttpServletRequest request){
        String singerName = request.getParameter("singerName");
        String songName = request.getParameter("songName");
        String songListId = request.getParameter("songListId");
        if(singerName!=null && songName!=null){
            String fullName=singerName+"-"+songName;
            Song song = songService.selectByFullName(fullName);
            if(song!=null){
                ListSong listSong=new ListSong();
                listSong.setFullName(fullName);
                listSong.setSongId(song.getId());
                listSong.setSongListId(Integer.parseInt(songListId));
                boolean insert = listSongService.insert(listSong);
                if(insert) {
                    return new Response(200, "添加成功", null);
                }else{
                    return new Response(500,"添加失败",null);
                }
                }else{
                return new Response(500,"添加失败",null);
            }
        }else{
            return new Response(500,"添加失败",null);
        }
    }

    @GetMapping("/deleteBySongIdAndSongListId/{songId}/{songListId}")
    public Response deleteBySongIdAndSongListId(@PathVariable("songId")String songId,@PathVariable("songListId")String songListId)
    {
        if(songId!=null && songListId!=null) {
            boolean flag = listSongService.deleteBySongIdAndSongListId(Integer.parseInt(songId), Integer.parseInt(songListId));
            if(flag){
                return new Response(200,"删除成功",null);
            }else{
                return new Response(500,"删除失败",null);
            }
        }else{
            return new Response(500,"删除失败",null);
        }
    }

    @DeleteMapping("/deleteByListSongId/{songId}")
    public Response deleteByListSongId(@PathVariable("songId")String songId){
        if(songId!=null) {
            boolean flag = listSongService.delete(Integer.parseInt(songId));
            if(flag){
                return new Response(200,"删除成功",null);
            }else{
                return new Response(500,"删除失败",null);
            }
        }else{
            return new Response(500,"删除失败",null);
        }
    }
}
