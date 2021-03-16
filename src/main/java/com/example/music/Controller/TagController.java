package com.example.music.Controller;

import com.example.music.Entity.Tag;
import com.example.music.Service.TagService;
import com.example.music.VO.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Jiangweixin
 * @Date: 2021/02/15/0:38
 * @Description:
 */
@RestController
@RequestMapping("/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/selectAllTag")
    public List<Tag> selectAllTag(){
        List<Tag> tags = tagService.selectAll();
        return tags;
    }

    @PostMapping("/insert")
    public Response insert(@RequestBody Tag tag){
        if(tag!=null){
            boolean flag = tagService.insert(tag);
            if(flag){
                return new Response(200,"新增成功",null);
            }else{
                return new Response(500,"新增失败",null);
            }
        }else{
            return new Response(500,"新增失败",null);
        }
    }

    @PostMapping("/update")
    public Response update(@RequestBody Tag tag){
        if(tag!=null){
            Tag tagQuery = tagService.selectById(tag.getId());
            BeanUtils.copyProperties(tag,tagQuery);
            boolean flag = tagService.update(tagQuery);
            if(flag){
                return new Response(200,"更新成功",null);
            }else{
                return new Response(500,"更新失败",null);
            }
        }else{
            return new Response(500,"更新失败",null);
        }
    }

    @DeleteMapping("/deleteById/{id}")
    public Response deleteById(@PathVariable("id")Integer id){
        boolean flag = tagService.deleteById(id);
        if(flag){
            return new Response(200,"删除成功",null);
        }else{
            return new Response(500,"删除失败",null);
        }
    }

}
