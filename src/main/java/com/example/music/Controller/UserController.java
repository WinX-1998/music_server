package com.example.music.Controller;

import com.example.music.Entity.Singer;
import com.example.music.Entity.User;
import com.example.music.Service.UserService;
import com.example.music.VO.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Jiangweixin
 * @Date: 2021/01/08/16:00
 * @Description:
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public Response addUser(@RequestBody User user){
        user.setAvator("/img/userPic/UserAvatar.jpg");
        Date date=new Date();
        user.setCreateTime(date);
        user.setUpdateTime(date);
        boolean flag = userService.insert(user);
        if(flag) {
            return new Response(200, "保存成功", null);
        }else{
            return new Response(500,"保存失败",null);
        }
    }

    /**
     * 更新用户
     */
    @PostMapping("/updateUser")
    public Response updateUser(@RequestBody User user){
        User queryUser = userService.selectByPrimaryKey(user.getId());
        if(queryUser!=null){
            BeanUtils.copyProperties(user,queryUser);
            queryUser.setUpdateTime(new Date());
            boolean flag = userService.update(queryUser);
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
     *  根据主键查询用户
     */
    @PostMapping("/selectUserById")
    public User selectUserById(@RequestParam("id") String id){
        int queryId = Integer.parseInt(id);
        User user = userService.selectByPrimaryKey(queryId);
        return user;
    }

    /**
     *  查找所有用户
     */
    @GetMapping("/selectAllUser")
    public List<User> selectAllUser(){
        return userService.selectAllUser();
    }


    @GetMapping("/selectUserCreateOnYear")
    public Map<Integer,Integer>selectUserCreateOnYear(){
        Date date=new Date();
        int year = date.getYear()+1900;
        Map<Integer, Integer> integerIntegerMap = userService.selectUserCreatTimeOnYear(year);
        return integerIntegerMap;
    }




    /**
     * 根据id删除歌手
     */
    @GetMapping("/deleteUser/{id}")
    public Response deleteUser(@PathVariable(value="id")int id){
        boolean flag = userService.delete(id);
        if(flag){
            return new Response(200,"删除成功",null);
        }else{
            return new Response(500,"删除失败",null);
        }
    }

    @GetMapping("/deleteUsers/{ids}")
    public Response deleteUsers(@PathVariable(value = "ids")String ids){
        String[] split = ids.split(",");
        List<Integer>list=new ArrayList<Integer>();
        for(int i=0;i<split.length;i++){
            int parseInt = Integer.parseInt(split[i]);
            list.add(parseInt);
        }
        boolean flag = userService.deleteUsers(list);
        if(flag){
            return new Response(200,"删除成功",null);
        }else{
            return new Response(500,"删除失败",null);
        }
    }


    /**
     * 更新歌手头像
     */
    @PostMapping(value="/updateUserAcator")
    public Response updateUserAcator(@RequestParam("file") MultipartFile avatorFile, @RequestParam("id")int id){
        if(avatorFile.isEmpty()){
            return new Response(500,"文件上传失败",null);
        }
        //文件名=当前时间到毫秒+原来的文件名
        String fileName=System.currentTimeMillis()+avatorFile.getOriginalFilename();
        //文件路径
        String filePath = System.getProperty("user.dir")+System.getProperty("file.separator")+"img"
                +System.getProperty("file.separator")+"userPic";
        //如果文件路径不存在，新增该路径
        File file=new File(filePath);
        if(!file.exists()){
            file.mkdir();
        }
        //实际的文件地址
        File dest=new File(filePath+System.getProperty("file.separator")+fileName);
        //存储到数据库里相对的文件地址
        String storeAvatorPath="/img/userPic/"+fileName;
        User user = userService.selectByPrimaryKey(id);
        String avator = user.getAvator();
        if(avator!=null){
            String subUrl = avator.substring(13);
            String oldFilePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "img"
                    + System.getProperty("file.separator") + "singerPic" + System.getProperty("file.separator") + subUrl;
            File file1 = new File(oldFilePath);
            file1.delete();
        }
        try {
            avatorFile.transferTo(dest);
            user.setAvator(storeAvatorPath);
            boolean flag = userService.update(user);
            if(flag){
                return new Response(200,"文件上传成功",storeAvatorPath);
            }else{
                return new Response(500,"文件上传失败",null);
            }
        } catch (IOException e) {
            return new Response(500,"文件上传失败"+e.getMessage(),null);
        }
    }
}
