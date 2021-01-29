package com.sports.service;

import com.sports.domain.Activity;
import com.sports.domain.User;

import java.util.List;

public interface UserAndActivityService {

    /**
     * 添加活动
     * @param creator 创建者名字
     * @param category 分类
     * @param information 活动介绍，信息
     * @param name 活动名字
     * @return
     */
    int addActivity(String creator, String category, String information, String name);

    /**
     * 删除活动
     * @param username  操作用户名
     * @param activityName 活动名
     * @return 成功：0  失败（没权限）：1
     */
    int deleteActivity(String username, String activityName);

    /**
     * 修改活动
     * @param username 操作用户名
     * @param activityName 活动名
     * @param information 活动介绍
     * @param category  活动分类
     * @return 成功：0  失败（没权限）：1
     */
    int updateActivity(String username,String activityName,String information,String category);

    /**
     * 加入活动
     * @param username 操作用户名
     * @param activityName 活动名
     * @return 成功：0  失败（没权限）：1
     */
    int joinActivity(String username, String activityName);

    /**
     * 退出活动
     * @param username 操作用户名
     * @param activityName 活动名
     * @return 成功：0  失败（没权限）：1
     */
    int exitActivity(String username, String activityName);

    /**
     * 根据用户查询活动列表
     * @param username 用户名
     * @return 查询结果
     */
    List<Activity> getActivityByUser(String username);

    /**
     * 根据活动名查询用户列表
     * @param activity 活动名
     * @return 查询结果
     */
    List<User> getUserByActivity(String activity);
}
