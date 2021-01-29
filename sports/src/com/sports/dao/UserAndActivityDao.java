package com.sports.dao;

import com.sports.domain.Activity;
import com.sports.domain.UserAndActivity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.HashMap;
import java.util.List;

/**
 * 用户和活动关系表的交互接口
 */
public interface UserAndActivityDao {
    /**
     * 添加关系
     * @param userAndActivity 关系信息
     */
    @Insert("insert into userandactivity values(null,#{user},#{activity})")
    void insertUserAndActivity(UserAndActivity userAndActivity);

    /**
     * 删除某一活动下的所有关系
     * @param activity 活动名
     */
    @Delete("delete from userandactivity where activity=#{0}")
    void deleteUserAndActivityByActivity(String activity);

    /**
     * 删除一条用户和活动关系
     * @param userAndActivity 用户和活动关系信息
     */
    @Delete("delete from userandactivity where user=#{user} and activity=#{activity}")
    void deleteUserAndActivity(UserAndActivity userAndActivity);

    /**
     * 根据用户名查询所有用户和活动关系
     * @param user 用户名
     * @return 返回关系队列
     */
    @Select("select * from userandactivity where user=#{0}")
    List<UserAndActivity> selectByUser(String user);

    /**
     * 根据活动名查询所有用户和活动关系
     * @param activity 活动名
     * @return 返回关系队列
     */
    @Select("select * from userandactivity where activity=#{0}")
    List<UserAndActivity> selectByActivity(String activity);

    @Update("Update userandactivity set user=#{newusername} where user=#{oldusername}")
    void update(HashMap<String,String> map);
}
