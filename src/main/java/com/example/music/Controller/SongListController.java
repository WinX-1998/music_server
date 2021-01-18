package com.example.music.Controller;

import com.example.music.Entity.Singer;
import com.example.music.Entity.SongList;
import com.example.music.Service.SongListService;
import com.example.music.VO.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Jiangweixin
 * @Date: 2021/01/06/21:33
 * @Description:
 */

@RestController
@RequestMapping("/songList")
public class SongListController {

    @Autowired
    private SongListService songListService;

    @PostMapping("/addSongList")
    public Response addSongList(@RequestBody SongList songList){
        boolean insert = songListService.insert(songList);
        if(insert){
            return new Response(200,"添加成功",null);
        }else{
            return new Response(500,"添加失败",null);
        }
    }

    @GetMapping("/selectAllSongList")
    public List<SongList> selectAllSongList(){
       return songListService.selectAllSongList();
    }

    @GetMapping("/selectTenSongLists")
    public List<SongList>selectTenSongLists(){
        List<SongList> songLists = songListService.selectTenSongLists();
        return songLists;
    }

    @PostMapping("/updateSongList")
    public Response updateSongList(@RequestBody SongList songList){
        if(songList.getId()!=null) {
            SongList oldSongList = songListService.selectByPrimaryKey(songList.getId());
            BeanUtils.copyProperties(songList,oldSongList);
            boolean flag = songListService.update(songList);
            if(flag){
                return new Response(200,"更新成功",null);
            }else{
                return new Response(500,"更新失败",null);
            }
        }else{
            return new Response(500,"更新失败",null);
        }
    }

    @DeleteMapping("/deleteSongList/{id}")
    public Response deleteSongList(@PathVariable("id")String id){
        if(id!=null){
            boolean flag = songListService.delete(Integer.parseInt(id));
            if(flag){
                return new Response(200,"删除成功",null);
            }else{
                return new Response(500,"删除失败",null);
            }
        }else{
            return new Response(500,"删除失败",null);
        }
    }

    @DeleteMapping("/deleteSongLists/{ids}")
    public Response deleteSongLists(@PathVariable("ids")String ids){
        String[] split = ids.split(",");
        List list=new ArrayList();
        for(int i=0;i<split.length;i++){
            int parseId = Integer.parseInt(split[i]);
            list.add(parseId);
        }
        boolean flag = songListService.deleteSongLists(list);
        if(flag){
            return new Response(200,"删除成功",null);
        }else{
            return new Response(500,"删除失败",null);
        }
    }


    /**
     * 更新歌手头像
     */
    @PostMapping(value="/updateSongListPic")
    public Response updateSongListPic(@RequestParam("file") MultipartFile avatorFile, @RequestParam("id")int id){
        if(avatorFile.isEmpty()){
            return new Response(500,"文件上传失败",null);
        }
        //文件名=当前时间到毫秒+原来的文件名
        String fileName=System.currentTimeMillis()+avatorFile.getOriginalFilename();
        //文件路径
        String filePath = System.getProperty("user.dir")+System.getProperty("file.separator")+"img"
                +System.getProperty("file.separator")+"songListPic";
        //如果文件路径不存在，新增该路径
        File file=new File(filePath);
        if(!file.exists()){
            file.mkdir();
        }
        //实际的文件地址
        File dest=new File(filePath+System.getProperty("file.separator")+fileName);
        //存储到数据库里相对的文件地址
        String storePicPath="/img/songListPic/"+fileName;
        SongList songList = songListService.selectByPrimaryKey(id);
        String pic = songList.getPic();
        if(pic!=null){
            String subUrl = pic.substring(14);
            String oldFilePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "img"
                    + System.getProperty("file.separator") + "songListPic" + System.getProperty("file.separator") + subUrl;
            File file1 = new File(oldFilePath);
            file1.delete();
        }
        try {
            avatorFile.transferTo(dest);

            songList.setPic(storePicPath);
            boolean flag = songListService.update(songList);
            if(flag){
                return new Response(200,"文件上传成功",null);
            }else{
                return new Response(500,"文件上传失败",null);
            }
        } catch (IOException e) {
            return new Response(500,"文件上传失败"+e.getMessage(),null);
        }
    }

}
