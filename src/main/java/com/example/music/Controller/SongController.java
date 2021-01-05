package com.example.music.Controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.example.music.Entity.Singer;
import com.example.music.Entity.Song;
import com.example.music.Service.SongService;
import com.example.music.VO.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

   /* @PostMapping("/addSong")
    public Response addSong(Song song, @RequestParam("file")MultipartFile mpfile){
        if(mpfile.isEmpty()){
            return new Response(500,"文件上传失败",null);
        }
        //文件名=当前时间到毫秒+原来的文件名
        String fileName=System.currentTimeMillis()+mpfile.getOriginalFilename();
        //文件路径
        String filePath = System.getProperty("user.dir")+System.getProperty("file.separator")+"songPic";
        //如果文件路径不存在，新增该路径
        File file=new File(filePath);
        if(!file.exists()){
            file.mkdir();
        }
        //实际的文件地址
        File dest=new File(filePath+System.getProperty("file.separator")+fileName);
        //存储到数据库里相对的文件地址
        String storeAvatorPath="/songPic/"+fileName;
        try {
            mpfile.transferTo(dest);
            song.setUrl(storeAvatorPath);
            boolean insert = songService.insert(song);
            if(insert){
                return new Response(200,"文件上传成功",storeAvatorPath);
            }else{
                return new Response(500,"文件上传失败",null);
            }
        } catch (IOException e) {
            return new Response(500,"文件上传失败"+e.getMessage(),null);
        }
    }*/


    @PostMapping("/addSong")
    public Response addSong(HttpServletRequest request, @RequestParam("file")MultipartFile mpfile){
        if(mpfile.isEmpty()){
            return new Response(500,"文件上传失败",null);
        }
        //文件名=当前时间到毫秒+原来的文件名
        String fileName=System.currentTimeMillis()+mpfile.getOriginalFilename();
        //文件路径
        String filePath = System.getProperty("user.dir")+System.getProperty("file.separator")+"song";
        //如果文件路径不存在，新增该路径
        File file=new File(filePath);
        if(!file.exists()){
            file.mkdir();
        }
        //实际的文件地址
        File dest=new File(filePath+System.getProperty("file.separator")+fileName);
        //存储到数据库里相对的文件地址
        String storeAvatorPath="/song/"+fileName;
        String name = request.getParameter("name");
        String introduction = request.getParameter("introduction");
        String lyric = request.getParameter("lyric");
        Song song=new Song();
        song.setName(name);
        song.setIntroduction(introduction);
        song.setLyric(lyric);
        song.setCreateTime(new Date());
        song.setUpdateTime(new Date());
        try {
            mpfile.transferTo(dest);
            song.setUrl(storeAvatorPath);
            boolean insert = songService.insert(song);
            if(insert){
                return new Response(200,"文件上传成功",storeAvatorPath);
            }else{
                return new Response(500,"文件上传失败",null);
            }
        } catch (IOException e) {
            return new Response(500,"文件上传失败"+e.getMessage(),null);
        }
    }


    @GetMapping("/selectAllSongs")
    public List<Song> selectAllSongs(){
        return songService.selectAllSongs();
    }

    /**
     * 更新歌曲
     */
    @PostMapping("/updateSong")
    public Response updateSong(@RequestBody Song song){
        Song querySong = songService.selectById(song.getId());
        if(querySong!=null){
            BeanUtils.copyProperties(song,querySong);
            boolean flag = songService.update(querySong);
            if(flag){
                return new Response(200,"更新成功",null);
            }else{
                return new Response(500,"更新失败",null);
            }
        }else{
            return new Response(500,"更新失败",null);
        }
    }

    /**
     * 更新歌曲头像
     */
    @PostMapping(value="/updateSongAcator")
    public Response updateSongAcator(@RequestParam("file")MultipartFile avatorFile,@RequestParam("id")int id){
        if(avatorFile.isEmpty()){
            return new Response(500,"文件上传失败",null);
        }
        //文件名=当前时间到毫秒+原来的文件名
        String fileName=System.currentTimeMillis()+avatorFile.getOriginalFilename();
        //文件路径
        String filePath = System.getProperty("user.dir")+System.getProperty("file.separator")+"img"
                +System.getProperty("file.separator")+"songPic";
        //如果文件路径不存在，新增该路径
        File file=new File(filePath);
        if(!file.exists()){
            file.mkdir();
        }
        //实际的文件地址
        File dest=new File(filePath+System.getProperty("file.separator")+fileName);
        //存储到数据库里相对的文件地址
        String storeAvatorPath="/img/songPic/"+fileName;
        try {
            avatorFile.transferTo(dest);
            Song song = songService.selectById(id);
            song.setPic(storeAvatorPath);
            boolean i = songService.update(song);
            if(i){
                return new Response(200,"文件上传成功",storeAvatorPath);
            }else{
                return new Response(500,"文件上传失败",null);
            }
        } catch (IOException e) {
            return new Response(500,"文件上传失败"+e.getMessage(),null);
        }
    }


    @DeleteMapping("/deleteSong/{id}")
    public Response deleteSong(@PathVariable("id")String id){
        if(id.isEmpty()) {
            return new Response(500,"删除失败",null);
        }
        int Id = Integer.parseInt(id);
        boolean flag = songService.delete(Id);
        if(flag){
            return new Response(200,"删除成功",null);
        }else{
            return new Response(500,"删除失败",null);
        }
    }


    @DeleteMapping("/deleteSongs/{ids}")
    public Response deleteSingers(@PathVariable(value = "ids")String ids){
        String[] split = ids.split(",");
        List<Integer>list=new ArrayList<Integer>();
        for(int i=0;i<split.length;i++){
            int parseInt = Integer.parseInt(split[i]);
            list.add(parseInt);
        }
        boolean flag = songService.deleteByIds(list);
        if(flag){
            return new Response(200,"删除成功",null);
        }else{
            return new Response(500,"删除失败",null);
        }
    }
}
