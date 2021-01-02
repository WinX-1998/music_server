package com.example.music.Service;

import com.example.music.Entity.Song;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Jiangweixin
 * @Date: 2020/12/30/16:06
 * @Description:
 */
public interface SongService {
    /**
     *增加
     */
    public boolean insert(Song song);

    /**
     *修改
     */
    public boolean update(Song song);

    /**
     * 删除
     */
    public boolean delete(Integer id);

    /**
     * 根据主键查询整个对象
     */
    public Song selectById(Integer id);

    /**
     * 查询所有歌曲
     */
    public List<Song> selectAllSongs();

    /**
     * 根据歌名精确查询列表
     */
    public List<Song> selectByName(String name);

    /**
     * 根据歌名模糊查询列表
     */
    public List<Song> selectByLikeName(String name);

    /**
     * 根据歌手id查询
     */
    public List<Song> selectBySingerId(Integer singerId);
}
