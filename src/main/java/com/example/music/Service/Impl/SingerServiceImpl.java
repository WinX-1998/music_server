package com.example.music.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.music.Entity.Singer;
import com.example.music.Mapper.SingerMapper;
import com.example.music.Service.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.tags.form.SelectTag;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Jiangweixin
 * @Date: 2020/12/30/14:17
 * @Description:
 */
@Service
public class SingerServiceImpl implements SingerService {
    @Autowired
    private SingerMapper singerMapper;

    @Override
    public int insert(Singer singer) {
        return singerMapper.insert(singer);

    }

    @Override
    public int update(Singer singer) {
        Date date=new Date();
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = dateFormat.format(date);

        return singerMapper.updateById(singer);

    }

    @Override
    public int delete(Integer id) {
        return singerMapper.deleteById(id);
    }

    @Override
    public Singer selectById(Integer id) {
        Singer singer = singerMapper.selectById(id);
        return singer;
    }

    @Override
    public List<Singer> selectAllSinger() {
        List<Singer> singers = singerMapper.selectList(null);
        return singers;
    }

    @Override
    public List<Singer> selectByLikeName(String name) {
        QueryWrapper<Singer>queryWrapper=new QueryWrapper<Singer>();
        queryWrapper.like("name", name);
        List<Singer> selectList = singerMapper.selectList(queryWrapper);
        return selectList;
    }

    @Override
    public List<Singer> selectBySex(Integer sex) {
        Map map=new HashMap<String,Object>();
        map.put("sex",sex);
        List list = singerMapper.selectByMap(map);
        return list;
    }
}
