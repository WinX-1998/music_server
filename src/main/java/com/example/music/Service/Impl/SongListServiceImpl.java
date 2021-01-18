package com.example.music.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.music.Entity.Song;
import com.example.music.Entity.SongList;
import com.example.music.Mapper.SongListMapper;
import com.example.music.Mapper.SongMapper;
import com.example.music.Service.SongListService;
import com.example.music.Service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Jiangweixin
 * @Date: 2021/01/05/18:03
 * @Description:
 */
@Service
public class SongListServiceImpl implements SongListService {

    @Autowired
    private SongListMapper songListMapper;


    @Override
    public boolean insert(SongList songList) {
        int insert = songListMapper.insert(songList);
        if(insert!=0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean update(SongList songList) {
        int i = songListMapper.updateById(songList);
        if(i!=0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean delete(Integer id) {
        int i = songListMapper.deleteById(id);
        if(i!=0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean deleteSongLists(List<Integer>ids){
        int i = songListMapper.deleteBatchIds(ids);
        if(i!=0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public SongList selectByPrimaryKey(Integer id) {
        SongList songList = (SongList) songListMapper.selectById(id);
        return songList;
    }

    @Override
    public List<SongList> selectAllSongList() {
        List list = songListMapper.selectList(null);
        return list;
    }

    @Override
    public List<SongList> selectTenSongLists() {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.last(" limit 0 , 10");
        List list = songListMapper.selectList(queryWrapper);
        return list;
    }


    @Override
    public List<SongList> songListOfTitle(String title) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("title",title);
        List list = songListMapper.selectList(queryWrapper);
        return list;
    }

    @Override
    public List<SongList> likeTitle(String title) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.like("title",title);
        List list = songListMapper.selectList(queryWrapper);
        return list;
    }

    @Override
    public List<SongList> likeStyle(String style) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.like("style",style);
        List list=songListMapper.selectList(queryWrapper);
        return list;
    }
}
