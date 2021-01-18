package com.example.music.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.music.Entity.Rank;
import com.example.music.Mapper.RankMapper;
import com.example.music.Service.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Jiangweixin
 * @Date: 2021/01/18/21:50
 * @Description:
 */
@Service
public class RankServiceImpl implements RankService {

    @Autowired
    private RankMapper rankMapper;

    @Override
    public boolean insert(Rank rank) {
        int insert = rankMapper.insert(rank);
        if(insert!=0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public int selectScoreSum(Integer songListId) {
        return rankMapper.selectScoreSum(songListId);
    }

    @Override
    public int selectRankNum(Integer songListId) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("song_list_id",songListId);
        List list = rankMapper.selectList(queryWrapper);
        if(list!=null){
            return list.size();
        }else {
            return 0;
        }
    }

    @Override
    public int rankOfSongListId(Integer songListId) {
        int sum = rankMapper.selectScoreSum(songListId);
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("song_list_id",songListId);
        List list = rankMapper.selectList(queryWrapper);
        if(list!=null){
              return sum/list.size();
        }else {
            return 0;
        }
    }
}
