package com.example.music.Controller;

import com.example.music.Entity.Singer;
import com.example.music.Service.SingerService;
import com.example.music.VO.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Jiangweixin
 * @Date: 2020/12/30/14:34
 * @Description:
 */

@RestController
@RequestMapping("/singer")
public class SingerController {

    @Autowired
    private SingerService singerService;



    @PostMapping("/add")
    public Response addSinger(@RequestBody Singer singer){
        singer.setPic("/img/singerPic/SingerAvatar.jpg");
        int insert = singerService.insert(singer);
        if(insert!=0) {
            return new Response(200, "保存成功", null);
        }else{
            return new Response(500,"保存失败",null);
        }
    }

        /**
         * 更新歌手
         */
        @PostMapping("/updateSinger")
        public Response updateSinger(@RequestBody Singer singer){
            Singer querySinger = singerService.selectById(singer.getId());
            if(querySinger!=null){
            BeanUtils.copyProperties(singer,querySinger);
            int update = singerService.update(querySinger);
            if(update>0){
                return new Response(200,"更新成功",null);
            }else{
                return new Response(500,"更新失败",null);
            }
            }else{
                return new Response(500,"更新失败",null);
            }
        }

        /**
        *  根据主键查询歌手
        */
        @GetMapping("/selectSingerById")
        public Singer selectSingerById(@RequestParam("id") String id){
            int queryId = Integer.parseInt(id);
            Singer singer = singerService.selectById(queryId);
            return singer;
        }

        /**
         *  查找所有歌手
         */
        @GetMapping("/selectAllSinger")
        public List<Singer> selectAllSinger(){
            return singerService.selectAllSinger();
        }


    /**
     * 根据歌手名字模糊查询列表
     */
    @GetMapping("/selectSingerByName")
    public List<Singer> selectSingerByName(@RequestParam("name")String name){
        List<Singer> singers = singerService.selectByLikeName(name);
        return singers;
    }

    /**
     * 根据性别查询
     */
    @GetMapping("/selectSingerBySex")
    public List<Singer> selectSingerBySex(@RequestParam("sex")String sex){
        int querySex = Integer.parseInt(sex);
        List<Singer> singers = singerService.selectBySex(querySex);
        return singers;
    }

    /**
     * 根据id删除歌手
     */
    @GetMapping("/deleteSinger/{id}")
    public Response deleteSinger(@PathVariable(value="id")int id){
        int delete = singerService.delete(id);
        if(delete!=0){
            return new Response(200,"删除成功",null);
        }else{
            return new Response(500,"删除失败",null);
        }
    }

    @GetMapping("/deleteSingers/{ids}")
    public Response deleteSingers(@PathVariable(value = "ids")String ids){
        String[] split = ids.split(",");
        List<Integer>list=new ArrayList<Integer>();
        for(int i=0;i<split.length;i++){
            int parseInt = Integer.parseInt(split[i]);
            list.add(parseInt);
        }
        int i = singerService.delectByIds(list);
        if(i!=0){
            return new Response(200,"删除成功",null);
        }else{
            return new Response(500,"删除失败",null);
        }
    }


    /**
     * 更新歌手头像
     */
    @PostMapping(value="/updateSingerAcator")
    public Response updateSingerAvator(@RequestParam("file")MultipartFile avatorFile,@RequestParam("id")int id){
        if(avatorFile.isEmpty()){
            return new Response(500,"文件上传失败",null);
        }
        //文件名=当前时间到毫秒+原来的文件名
        String fileName=System.currentTimeMillis()+avatorFile.getOriginalFilename();
        //文件路径
        String filePath = System.getProperty("user.dir")+System.getProperty("file.separator")+"img"
                +System.getProperty("file.separator")+"singerPic";
        //如果文件路径不存在，新增该路径
        File file=new File(filePath);
        if(!file.exists()){
            file.mkdir();
        }
        //实际的文件地址
        File dest=new File(filePath+System.getProperty("file.separator")+fileName);
        //存储到数据库里相对的文件地址
        String storeAvatorPath="/img/singerPic/"+fileName;
        try {
            avatorFile.transferTo(dest);
            Singer singer = singerService.selectById(id);
            singer.setPic(storeAvatorPath);
            int i = singerService.update(singer);
            if(i!=0){
                return new Response(200,"文件上传成功",storeAvatorPath);
            }else{
                return new Response(500,"文件上传失败",null);
            }
        } catch (IOException e) {
            return new Response(500,"文件上传失败"+e.getMessage(),null);
        }
    }


}
