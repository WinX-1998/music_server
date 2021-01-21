package com.example.music.Service;

import com.example.music.Entity.Collect;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Jiangweixin
 * @Date: 2021/01/20/14:35
 * @Description:
 */
public interface CollectService {
    /**
     *增加
     */
    public boolean insert(Collect collect);

    /**
     * 删除
     */
    public boolean delete(Integer id);

    /**
     * 根据用户id和歌曲id删除
     */
    public boolean deleteByUserIdSongId(Integer userId, Integer songId);

    /**
     * 根据用户id和歌单id删除
     */
    public boolean deleteByUserIdSongListId(Integer userId, Integer songListId);

    /**
     * 查询所有收藏
     */
    public List<Collect> selectAllCollet();

    /**
     * 查询某个用户的收藏列表
     */
    public List<Collect> collectOfUserId(Integer userId);

    /**
     * 查询某个用户是否已经收藏了某个歌曲
     */
    public boolean existSongId(@Param("userId") Integer userId, @Param("songId") Integer songId);

    /**
     * 查询某个用户是否已经收藏了某个歌曲
     */
    public boolean existSongListId(@Param("userId") Integer userId, @Param("songListId") Integer songListId);
}
