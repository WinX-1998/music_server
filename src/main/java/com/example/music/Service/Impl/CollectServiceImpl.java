package com.example.music.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.music.Entity.Collect;
import com.example.music.Mapper.CollectMapper;
import com.example.music.Service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Jiangweixin
 * @Date: 2021/01/20/14:37
 * @Description:
 */
@Service
public class CollectServiceImpl implements CollectService {

    @Autowired
    private CollectMapper collectMapper;


    @Override
    public boolean insert(Collect collect) {
        int insert = collectMapper.insert(collect);
        if(insert!=0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean delete(Integer id) {
        int delete = collectMapper.deleteById(id);
        if(delete!=0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean deleteByUserIdSongId(Integer userId, Integer songId) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("user_id",userId);
        queryWrapper.eq("song_id",songId);
        int delete = collectMapper.delete(queryWrapper);
        if(delete!=0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    @Transactional
    public boolean deleteCollectionsByUserId(Integer userId,List<Integer>ids) {
        try {
            for (Integer id : ids) {
                QueryWrapper queryWrapper = new QueryWrapper();
                queryWrapper.eq("user_id", userId);
                queryWrapper.eq("song_id", id);
                collectMapper.delete(queryWrapper);
            }
        }catch (Exception e){
            return false;
        }
        return true;
    }


    @Override
    public boolean deleteByUserIdSongListId(Integer userId, Integer songListId) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("user_id",userId);
        queryWrapper.eq("song_list_id",songListId);
        int delete = collectMapper.delete(queryWrapper);
        if(delete!=0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public List<Collect> selectAllCollet() {
        List<Collect> collects = collectMapper.selectList(null);
        return collects;
    }

    @Override
    public List<Collect> collectOfUserId(Integer userId) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("user_id",userId);
        List list = collectMapper.selectList(queryWrapper);
        return list;
    }

    @Override
    public Collect selectCollectionByUserIdAndSongId(Integer userId, Integer songId) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("user_id",userId);
        queryWrapper.eq("song_id",songId);
        Collect collect = collectMapper.selectOne(queryWrapper);
        return collect;
    }

    @Override
    public boolean existSongId(Integer userId, Integer songId) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("user_id",userId);
        queryWrapper.eq("song_id",songId);
        Collect collect = collectMapper.selectOne(queryWrapper);
        if(collect!=null){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean existSongListId(Integer userId, Integer songListId) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("user_id",userId);
        queryWrapper.eq("song_list_id",songListId);
        Collect collect = collectMapper.selectOne(queryWrapper);
        if(collect!=null){
            return true;
        }else {
            return false;
        }
    }
}
