package com.example.music.Controller;

import com.example.music.Entity.Comment;
import com.example.music.Service.CommentService;
import com.example.music.VO.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    @PostMapping("/addComment")
    public Response addComment(@RequestBody Comment comment){
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


    @GetMapping("/selectCommentById/{id}")
    public Response selectCommentById(@PathVariable("id")String id){
        if(!id.isEmpty()){
            Comment comment = commentService.selectByPrimaryKey(Integer.parseInt(id));
            return new Response(200,"查找成功",comment);
        }else{
            return new Response(500,"查找失败",null);
        }
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
    public List<Comment> selectCommentBySongListId(@PathVariable("id")String songListId){
        if(!songListId.isEmpty()) {
            List<Comment> comments = commentService.commentOfSongListId(Integer.parseInt(songListId));
            return comments;
        }else{
            return null;
        }
    }





}
