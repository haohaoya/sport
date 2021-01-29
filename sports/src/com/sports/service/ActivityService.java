package com.sports.service;

import com.sports.domain.Activity;
import com.sports.domain.Category;

import java.util.List;

public interface ActivityService {
    /**
     * 获取所有活动
     * @return
     */
    List<Activity> getAllActivity();

    /**
     * 获取某分类下的活动
     * @param category
     * @return
     */
    List<Activity> getSomeActivityByCategory(String category);

    /**
     * 根据活动名字模糊获取活动
     * @param name
     * @return
     */
    List<Activity> getSomeActivityByName(String name);

    /**
     * 根据活动名获取活动
     * @param name
     * @return
     */
    Activity getActivityByName(String name);

    String getNumByActivity(String activity);

}
