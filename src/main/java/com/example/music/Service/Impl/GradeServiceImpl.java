package com.example.music.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.music.Entity.Grade;
import com.example.music.Mapper.GradeMapper;
import com.example.music.Service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Jiangweixin
 * @Date: 2021/01/18/21:50
 * @Description:
 */
@Service
public class GradeServiceImpl implements GradeService {

    @Autowired
    private GradeMapper gradeMapper;

    @Override
    public boolean insert(Grade grade) {
        int insert = gradeMapper.insert(grade);
        if(insert!=0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public int selectScoreSum(Integer songListId) {
        return gradeMapper.selectScoreSum(songListId);
    }

    @Override
    public int selectGradeNum(Integer songListId) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("song_list_id",songListId);
        List list = gradeMapper.selectList(queryWrapper);
        if(list!=null){
            return list.size();
        }else {
            return 0;
        }
    }

    @Override
    public int gradeOfSongListId(Integer songListId) {
        int sum=gradeMapper.selectScoreSum(songListId);
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("song_list_id",songListId);
        List list = gradeMapper.selectList(queryWrapper);
        if(list.size()!=0){
              return sum/list.size();
        }else {
            return 0;
        }
    }
}
