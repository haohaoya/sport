package com.sports.service.impl;

import com.sports.dao.ActivityDao;
import com.sports.dao.CategoryDao;
import com.sports.dao.UserAndActivityDao;
import com.sports.domain.Activity;
import com.sports.domain.Category;
import com.sports.domain.UserAndActivity;
import com.sports.service.ActivityService;
import com.sports.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * 活动服务实现类
 * 方法注释见对应接口
 */
public class ActivityServiceImpl implements ActivityService {
    @Override
    public List<Activity> getAllActivity() {
        SqlSession session = MyBatisUtil.getSession();
        ActivityDao activityDao = session.getMapper(ActivityDao.class);
        List<Activity> activities = activityDao.selectAll();
        MyBatisUtil.closeSession();
        return activities;
    }

    @Override
    public List<Activity> getSomeActivityByCategory(String category) {
        SqlSession session = MyBatisUtil.getSession();
        ActivityDao activityDao = session.getMapper(ActivityDao.class);
        List<Activity> activities = activityDao.selectSomeByCategory(category);
        MyBatisUtil.closeSession();
        return activities;
    }
    @Override
    public List<Activity> getSomeActivityByName(String name) {
        String dimName = "%"+name+"%";
        System.out.println(dimName);
        SqlSession session = MyBatisUtil.getSession();
        ActivityDao activityDao = session.getMapper(ActivityDao.class);
        List<Activity> activities = activityDao.selectSomeByName(dimName);
        MyBatisUtil.closeSession();
        return activities;
    }

    @Override
    public Activity getActivityByName(String name) {
        SqlSession session = MyBatisUtil.getSession();
        ActivityDao activityDao = session.getMapper(ActivityDao.class);
        Activity activity = activityDao.selectByName(name);
        MyBatisUtil.closeSession();
        return activity;
    }

    public String getNumByActivity(String activity){
        SqlSession session = MyBatisUtil.getSession();
        UserAndActivityDao userAndActivityDao = session.getMapper(UserAndActivityDao.class);
        List<UserAndActivity> userAndActivities = userAndActivityDao.selectByActivity(activity);
        MyBatisUtil.closeSession();
        return userAndActivities.size()+"";
    }

}
