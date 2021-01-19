package com.example.music.Service;

import com.example.music.Entity.Grade;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Jiangweixin
 * @Date: 2021/01/18/21:49
 * @Description:
 */
public interface GradeService {
    /**
     *增加
     */
    public boolean insert(Grade grade);

    /**
     * 查总分
     */
    public int selectScoreSum(Integer songListId);

    /**
     * 查总评分人数
     */
    public int selectGradeNum(Integer songListId);

    /**
     * 计算平均分
     */
    public int gradeOfSongListId(Integer songListId);
}
