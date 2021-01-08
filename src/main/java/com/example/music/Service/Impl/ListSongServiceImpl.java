package com.example.music.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.music.Entity.ListSong;
import com.example.music.Mapper.ListSongMapper;
import com.example.music.Service.ListSongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Jiangweixin
 * @Date: 2021/01/07/13:54
 * @Description:
 */
@Service
public class ListSongServiceImpl implements ListSongService {

    @Autowired
    private ListSongMapper listSongMapper;
    @Override
    public boolean insert(ListSong listSong) {
        int insert = listSongMapper.insert(listSong);
        if(insert!=0){
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean update(ListSong listSong) {
        int i = listSongMapper.updateById(listSong);
        if(i!=0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean delete(Integer id) {
        int i = listSongMapper.deleteById(id);
        if(i!=0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean deleteBySongIdAndSongListId(Integer songId, Integer songListId) {
        Map map=new HashMap();
        map.put("song_Id",songId);
        map.put("song_List_Id",songListId);
        int i = listSongMapper.deleteByMap(map);
        if(i!=0){
            return true;
        }else {
            return false;
        }
        }

    @Override
    public ListSong selectByPrimaryKey(Integer id) {
        return listSongMapper.selectById(id);
    }

    @Override
    public List<ListSong> allListSong() {
        return  listSongMapper.selectList(null);
    }

    @Override
    public List<ListSong> listSongOfSongListId(Integer songListId) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("song_List_Id",songListId);
        List list = listSongMapper.selectList(queryWrapper);
        return list;
    }
}
