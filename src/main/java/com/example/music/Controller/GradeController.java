package com.example.music.Controller;

import com.example.music.Entity.Grade;
import com.example.music.Service.GradeService;
import com.example.music.VO.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 *
 *
 * @Auther: Jiangweixin
 * @Date: 2021/01/18/22:17
 * @Description:
 */
@RestController
@RequestMapping("/grade")
public class GradeController {

    @Autowired
    private GradeService gradeService;


    @PostMapping("/addGrade")
    public Response addRank(HttpServletRequest request){
        String songListId = request.getParameter("songListId");
        String userId = request.getParameter("userId");
        String score = request.getParameter("score");
        Grade grade=new Grade();
        grade.setSongListId(Integer.parseInt(songListId));
        grade.setScore(Integer.parseInt(score));
        grade.setUserId(Integer.parseInt(userId));
        try{
            boolean flag = gradeService.insert(grade);
            if(flag){
                return new Response(200,"评价成功",null);
            }else{
                return new Response(500,"评价失败",null);
            }
        }catch (Exception e){
            return new Response(500,"您已经评价过一次啦",null);
        }


    }

    @GetMapping("/gradeOfSongListId/{id}")
    public int gradeOfSongListId(@PathVariable("id")String id){
        if(!id.isEmpty()) {
            return gradeService.gradeOfSongListId(Integer.parseInt(id));
        }else{
            return 0;
        }
        }



}
