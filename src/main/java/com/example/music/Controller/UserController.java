package com.example.music.Controller;

import com.example.music.Entity.User;
import com.example.music.Service.UserService;
import com.example.music.VO.Response;
import com.example.music.VO.UserInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
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
        String username = user.getUsername();
        if(userService.selectByUserName(username)!=null){
            return new Response(500,"该名字已被使用",null);
        }
        String password = user.getPassword();
        String salt=new SecureRandomNumberGenerator().nextBytes().toString();
        int times=2;
        String algorithm="md5";
        String pwdAfterHash=new SimpleHash(algorithm,password,salt,times).toString();
        user.setPassword(pwdAfterHash);
        user.setSalt(salt);
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
     * 用户登录
     */
    @PostMapping("/login")
    public Response Login(@RequestBody User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        User userQuery = userService.selectByUserName(username);
        Integer id = userQuery.getId();
        String avator = userQuery.getAvator();
        Boolean isVip=userQuery.getIsVip();
        System.out.println(id);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
        try {
            subject.login(usernamePasswordToken);
            return new Response(200, "success", new UserInfo(id,username,avator,isVip));
        } catch (AuthenticationException e) {
            return new Response(500, "failure", null);
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

    @PostMapping("/changeIsVip")
    public Response changeIsVip(HttpServletRequest request){
        String isVip = request.getParameter("isVip");
        String id = request.getParameter("id");
        Boolean isVip1=false;
        if(isVip.equals("true")){
            isVip1=true;
        }
        boolean flag = userService.changeIsVip(Integer.parseInt(id), isVip1);
        if(flag){
            return new Response(200,"更新状态成功",null);
        }else{
            return new Response(500,"更新状态失败",null);
        }
    }

    /**
     *  根据主键查询用户
     */
    @GetMapping("/selectUserById/{id}")
    public User selectUserById(@PathVariable("id") String id){
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
    @RequiresPermissions("user:delete")
    @GetMapping("/deleteUser/{id}")
    public Response deleteUser(@PathVariable(value="id")int id){
            boolean flag = userService.delete(id);
            if (flag) {
                return new Response(200, "删除成功", null);
            } else {
                return new Response(500, "删除失败", null);
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

    @GetMapping("/getAcator/{id}")
    public String getAcator(@PathVariable("id")String id){
        User user = userService.selectByPrimaryKey(Integer.parseInt(id));
        String avator = user.getAvator();
        return avator;
    }

    @PostMapping("/setPassword")
    public Response setPassword(HttpServletRequest request){
        String id = request.getParameter("id");
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        User user = userService.selectByPrimaryKey(Integer.parseInt(id));
        String password = user.getPassword();
        String salt = user.getSalt();
        String hashAlgorithmName = "MD5";
        int hashIterations = 2;
        String result = new SimpleHash(hashAlgorithmName,oldPassword,salt,hashIterations).toString();
        if(result.equals(password)){
            String salt1=new SecureRandomNumberGenerator().nextBytes().toString();
            int times=2;
            String algorithm="md5";
            String pwdAfterHash=new SimpleHash(algorithm,newPassword,salt1,times).toString();
            user.setSalt(salt1);
            user.setPassword(pwdAfterHash);
            userService.update(user);
            return new Response(200,"修改密码成功",null);
        }else{
            return new Response(500,"旧密码不正确",null);
        }

    }


    /**
     * 更新用户头像
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
                    + System.getProperty("file.separator") + "userPic" + System.getProperty("file.separator") + subUrl;
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
