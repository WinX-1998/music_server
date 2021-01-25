package com.example.music.Controller;

import com.example.music.Entity.Admin;
import com.example.music.Entity.CustomizedToken;
import com.example.music.Entity.LoginType;
import com.example.music.Entity.User;
import com.example.music.Service.AdminService;
import com.example.music.VO.Response;
import com.example.music.VO.UserInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

   /* @PostMapping("/login")
    public Response login(@RequestBody User user){
        String username = user.getUsername();
        String password = user.getPassword();
        User userQuery = userService.findUserByUsername(username);
        Long id = userQuery.getId();
        Subject subject= SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken=new UsernamePasswordToken(username,password);
        try {
            subject.login(usernamePasswordToken);
            return new Response(200, "success", new UserInfo(username, password, id));
        }catch (AuthenticationException e) {
            return new Response(500, "failure", null);
        }
    }*/



   @PostMapping("/login")
   public String login(@RequestBody Admin admin){
       String adminName = admin.getAdminName();
       String password = admin.getPassword();
       Admin adminQuery = adminService.findAdminByAdminName(adminName);
       Integer id = adminQuery.getId();
       System.out.println(id);
       Subject subject = SecurityUtils.getSubject();
       CustomizedToken customizedToken = new CustomizedToken(adminName, password, LoginType.ADMIN.toString());
       try {
           subject.login(customizedToken);
           boolean role_user_admin = subject.hasRole("role_user_admin");
           boolean permitted = subject.isPermitted("user:delete");
           System.out.println("per:"+permitted);
           System.out.println(role_user_admin);
           return "success";
       } catch (AuthenticationException e) {
           return "fail";
       }
   }


   @PostMapping("/register")
   public Response register(@RequestBody Admin admin){
       String adminName = admin.getAdminName();
       if(adminService.findAdminByAdminName(adminName)!=null){
           return new Response(500,"该名字已被使用",null);
       }
       String password = admin.getPassword();
       String salt=new SecureRandomNumberGenerator().nextBytes().toString();
       int times=2;
       String algorithm="md5";
       String pwdAfterHash=new SimpleHash(algorithm,password,salt,times).toString();
       admin.setPassword(pwdAfterHash);
       admin.setSalt(salt);
       boolean flag = adminService.addAdmin(admin);
       if(flag) {
           return new Response(200, "保存成功", null);
       }else{
           return new Response(500,"保存失败",null);
       }
   }

}
