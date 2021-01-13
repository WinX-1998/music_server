package com.example.music.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.music.Entity.Song;
import com.example.music.Mapper.SongMapper;
import com.example.music.Service.SongService;
import com.example.music.VO.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Jiangweixin
 * @Date: 2020/12/30/16:09
 * @Description:
 */
@Service
public class SongServiceImpl implements SongService {

    @Autowired
    private SongMapper songMapper;

    @Override
    public boolean insert(Song song) {
        int insert = songMapper.insert(song);
        if(insert!=0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean update(Song song) {
        int i = songMapper.updateById(song);
        if (i != 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean delete(Integer id) {
        int i=songMapper.deleteById(id);
        if(i!=0){
            return true;
        }else{
        return false;
        }
    }

    @Override
    public boolean deleteByIds(List<Integer> ids) {
        int i = songMapper.deleteBatchIds(ids);
        if(i!=0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Song selectById(Integer id) {
        return songMapper.selectById(id);
    }

    @Override
    public List<Song> selectAllSongs() {
        List<Song> songs = songMapper.selectList(null);
        return songs;
    }

    @Override
    public List<Song> selectTenSongs() {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.last("limit 0,10");
        List list = songMapper.selectList(queryWrapper);
        return list;
    }

    @Override
    public List<Song> selectByName(String name) {
        Map map=new HashMap<String,Object>();
        map.put("name",name);
        List list = songMapper.selectByMap(map);
        return list;
    }

    @Override
    public Song selectByFullName(String fullName) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("full_name",fullName);
        Song song = songMapper.selectOne(queryWrapper);
        return song;
    }

    @Override
    public List<Song> selectByLikeName(String name) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.like("name",name);
        List list = songMapper.selectList(queryWrapper);
        return list;
    }

    @Override
    public List<Song> selectBySingerId(Integer singerId) {
        Map map=new HashMap();
        map.put("singer_id",singerId);
        List list = songMapper.selectByMap(map);
        return list;
    }
}
