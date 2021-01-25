package com.example.music.Controller;

import com.example.music.Entity.Comment;
import com.example.music.Entity.User;
import com.example.music.Service.CommentService;
import com.example.music.Service.UserService;
import com.example.music.VO.CommentInfo;
import com.example.music.VO.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Jiangweixin
 * @Date: 2021/01/19/16:35
 * @Description:
 */
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @PostMapping("/addComment")
    public Response addComment(HttpServletRequest request){
        String songId = request.getParameter("songId");
        String songListId=request.getParameter("songListId");
        String userId = request.getParameter("userId");
        String type = request.getParameter("type");
        String content = request.getParameter("content").trim();
        Date date=new Date();
        Comment comment=new Comment();
        if(Integer.parseInt(type)==0){
            comment.setSongId(Integer.parseInt(songId));
        }else{
            comment.setSongListId(Integer.parseInt(songListId));
        }
        comment.setUserId(Integer.parseInt(userId));
        comment.setContent(content);
        comment.setCreateTime(date);
        comment.setType(Integer.parseInt(type));
        boolean flag = commentService.insert(comment);
        if(flag){
            return new Response(200,"评论成功",null);
        }else{
            return new Response(500,"评论失败",null);
        }
    }

    @PostMapping("/updateComment")
    public Response updateComment(@RequestBody Comment comment){
        Integer id = comment.getId();
        Comment commentQuery = commentService.selectByPrimaryKey(id);
        BeanUtils.copyProperties(comment,commentQuery);
        boolean flag = commentService.update(commentQuery);
        if(flag){
            return new Response(200,"更新成功",null);
        }else{
            return new Response(500,"更新失败",null);
        }
    }

    @DeleteMapping("/deleteComment/{id}")
    public Response deleteComment(@PathVariable("id")String id){
        if(!id.isEmpty()){
            boolean flag = commentService.delete(Integer.parseInt(id));
            if(flag){
                return new Response(200,"删除成功",null);
            }else{
                return new Response(500,"删除失败",null);
            }
        }else{
            return new Response(500,"删除失败",null);
        }
    }

    @GetMapping("/deleteComments/{ids}")
    public Response deleteComments(@PathVariable("ids")String ids){
        String[] split = ids.split(",");
        List<Integer>list=new ArrayList<Integer>();
        for(int i=0;i<split.length;i++){
            int parseInt = Integer.parseInt(split[i]);
            list.add(parseInt);
        }
        boolean flag = commentService.deleteComments(list);
        if(flag){
            return new Response(200,"删除成功",null);
        }else{
            return new Response(500,"删除失败",null);
        }
    }


    @GetMapping("/selectCommentById/{id}")
    public Response selectCommentById(@PathVariable("id")String id){
        if(!id.isEmpty()){
            Comment comment = commentService.selectByPrimaryKey(Integer.parseInt(id));
            return new Response(200,"查找成功",comment);
        }else{
            return new Response(500,"查找失败",null);
        }
    }

    @GetMapping("/selectCommentsBySongId/{id}")
    public List<Comment>selectCommentsBySongId(@PathVariable("id")String id){
        List<Comment> comments = commentService.selectBySongId(Integer.parseInt(id));
        return comments;
    }

    /**
     * 查询所有评论
     */
    @GetMapping(value = "/selectAllComment")
    public List<Comment> selectAllComment(){
        List<Comment> comments = commentService.allComment();
        return comments;
    }

    /**
     * 查询某个歌曲下的所有评论
     */
    @GetMapping(value = "/selectCommentBySongId/{id}")
    public List<Comment> selectCommentBySongId(@PathVariable("id")String songId){
      if(!songId.isEmpty()) {
          List<Comment> comments = commentService.commentOfSongId(Integer.parseInt(songId));
          return comments;
      }else{
          return null;
      }
    }

    /**
     * 查询某个歌单下的所有评论
     */
    @GetMapping(value = "/selectCommentBySongListId/{id}")
    public List<CommentInfo> selectCommentBySongListId(@PathVariable("id")String songListId){
        if(!songListId.isEmpty()) {
            List<Comment> comments = commentService.commentOfSongListId(Integer.parseInt(songListId));
            List<CommentInfo>list=new ArrayList<>();
            for(Comment comment:comments){
                CommentInfo commentInfo=new CommentInfo();
                User user = userService.selectByPrimaryKey(comment.getUserId());
                commentInfo.setUserId(user.getId());
                commentInfo.setUsername(user.getUsername());
                commentInfo.setContent(comment.getContent());
                commentInfo.setCommentId(comment.getId());
                commentInfo.setCreateTime(comment.getCreateTime());
                commentInfo.setUp(comment.getUp());
                list.add(commentInfo);
            }
            return list;
        }else{
            return null;
        }
    }


    /**
     * 给某个评论点赞
     */
    @PostMapping("/like")
    public Response like(HttpServletRequest request){
        String id = request.getParameter("id").trim(); //评论主键
        String up = request.getParameter("up").trim();
        //保存到评论的对象中
        Comment comment = new Comment();
        comment.setId(Integer.parseInt(id));
        comment.setUp(Integer.parseInt(up));

        boolean flag = commentService.update(comment);
        if(flag){
           return new Response(200,"保存成功",null);
        } else{
          return new Response(500,"保存失败",null);
        }
    }

}
