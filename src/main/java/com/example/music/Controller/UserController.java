package com.example.music.Controller;

import com.example.music.Entity.User;
import com.example.music.Service.UserService;
import com.example.music.VO.Response;
import com.example.music.VO.UserInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

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
   public String login(@RequestBody User user){
       String username = user.getUsername();
       User userQuery = userService.findUserByUsername(username);
       if(userQuery!=null){
           return "success";
       }else{
           return "failure";
       }
   }
}
