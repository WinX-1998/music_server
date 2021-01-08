package com.example.music.Controller;

import com.example.music.Entity.Admin;
import com.example.music.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
       Admin adminQuery = adminService.findAdminByAdminName(adminName);
       if(adminQuery !=null){
           return "success";
       }else{
           return "failure";
       }
   }
}
