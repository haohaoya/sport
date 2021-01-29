package com.sports.dao;

import com.sports.domain.Discuss;
import com.sports.domain.UserAndActivity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 评论表的交互接口
 */
public interface DiscussDao {
    /**
     * 添加评论
     * @param discuss 评论信息
     */
    @Insert("insert into discuss values(null,#{information},#{creator},#{activity})")
    void insertDiscuss(Discuss discuss);

    /**
     * 删除某一活动下的所有评论
     * @param activity 活动名
     */
    @Delete("delete from discuss where activity=#{0}")
    void deleteDiscussByActivity(String activity);

    /**
     * 查询某一活动下的所有评论
     * @param activity 活动名
     * @return 查询到的评论列表
     */
    @Select("select * from discuss where activity=#{0}")
    List<Discuss> selectByActivity(String activity);
}
