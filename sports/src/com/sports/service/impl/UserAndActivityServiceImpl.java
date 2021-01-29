package com.sports.service.impl;

import com.sports.dao.ActivityDao;
import com.sports.dao.DiscussDao;
import com.sports.dao.UserAndActivityDao;
import com.sports.dao.UserDao;
import com.sports.domain.Activity;
import com.sports.domain.Discuss;
import com.sports.domain.User;
import com.sports.domain.UserAndActivity;
import com.sports.service.UserAndActivityService;
import com.sports.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import javax.jws.soap.SOAPBinding;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * 用户和活动服务实现类
 * 方法注释见对应接口
 */
public class UserAndActivityServiceImpl implements UserAndActivityService {
    @Override
    public int addActivity(String creator, String category, String information, String name) {
        SqlSession session = MyBatisUtil.getSession();
        ActivityDao activityDao = session.getMapper(ActivityDao.class);
        UserAndActivityDao userAndActivityDao = session.getMapper(UserAndActivityDao.class);
        Activity activity = activityDao.selectByName(name);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formatStr =formatter.format(new Date());
        System.out.println(activity);
        if(activity==null){
            //活动名没有重复
            activity = new Activity();
            activity.setName(name);
            activity.setInformation(information);
            activity.setCreator(creator);
            activity.setCategory(category);
            activity.setTime(formatStr);
            UserAndActivity userAndActivity = new UserAndActivity();
            userAndActivity.setUser(creator);
            userAndActivity.setActivity(name);
            try{
                activityDao.insertActivity(activity);
                userAndActivityDao.insertUserAndActivity(userAndActivity);
                session.commit();
            }catch (Exception e){
                //出现异常，数据库回滚
                session.rollback();
                e.printStackTrace();
                return 1;
            }finally {
                MyBatisUtil.closeSession();
            }
            return 0;
        }
        MyBatisUtil.closeSession();
        return 1;

    }

    @Override
    public int deleteActivity(String username, String activityName) {
        //判断权限
        SqlSession session = MyBatisUtil.getSession();
        ActivityDao activityDao = session.getMapper(ActivityDao.class);
        UserAndActivityDao userAndActivityDao = session.getMapper(UserAndActivityDao.class);
        DiscussDao discussDao = session.getMapper(DiscussDao.class);

        Activity activity = activityDao.selectByName(activityName);
        if(activity!=null && activity.getCreator().equals(username)){
            //是当前用户创建的活动
            try{
                userAndActivityDao.deleteUserAndActivityByActivity(activityName);
                activityDao.deleteActivityByName(activityName);
                discussDao.deleteDiscussByActivity(activityName);
                session.commit();
            }catch (Exception e){
                //出现异常，数据库回滚
                session.rollback();
                e.printStackTrace();
                return 1;
            }finally {
                MyBatisUtil.closeSession();
            }
            return 0;
        }else{
            MyBatisUtil.closeSession();
            return 1;
        }
    }

    @Override
    public int updateActivity(String username, String activityName, String information, String category) {
        //判断权限
        SqlSession session = MyBatisUtil.getSession();
        ActivityDao activityDao = session.getMapper(ActivityDao.class);

        Activity activity = activityDao.selectByName(activityName);
        if(activity!=null && activity.getCreator().equals(username)){
            //是当前用户创建的活动
            try{
                activity.setInformation(information);
                activity.setCategory(category);
                activityDao.updateActivityByName(activity);
                session.commit();
            }catch (Exception e){
                //出现异常，数据库回滚
                session.rollback();
                e.printStackTrace();
                return 1;
            }finally {
                MyBatisUtil.closeSession();
            }
            return 0;
        }else{
            MyBatisUtil.closeSession();
            //不是当前用户创建的活动
            return 1;
        }
    }

    @Override
    public int joinActivity(String username, String activityName) {
        //判断权限
        SqlSession session = MyBatisUtil.getSession();
        ActivityDao activityDao = session.getMapper(ActivityDao.class);
        UserAndActivityDao userAndActivityDao = session.getMapper(UserAndActivityDao.class);

        Activity activity = activityDao.selectByName(activityName);
        if(activity!=null){
            //是当前用户创建的活动
            try{
                UserAndActivity userAndActivity = new UserAndActivity();
                userAndActivity.setUser(username);
                userAndActivity.setActivity(activityName);
                userAndActivityDao.insertUserAndActivity(userAndActivity);
                session.commit();
            }catch (Exception e){
                //出现异常，数据库回滚
                session.rollback();
                e.printStackTrace();
                return 1;
            }finally {
                MyBatisUtil.closeSession();
            }
            return 0;
        }else{
            MyBatisUtil.closeSession();
            //不是当前用户创建的活动
            return 1;
        }
    }

    @Override
    public int exitActivity(String username, String activityName) {
        //判断权限
        SqlSession session = MyBatisUtil.getSession();
        ActivityDao activityDao = session.getMapper(ActivityDao.class);
        UserAndActivityDao userAndActivityDao = session.getMapper(UserAndActivityDao.class);

        Activity activity = activityDao.selectByName(activityName);
        if(activity!=null){
            //是当前用户退出活动
            try{
                UserAndActivity userAndActivity = new UserAndActivity();
                userAndActivity.setUser(username);
                userAndActivity.setActivity(activityName);
                userAndActivityDao.deleteUserAndActivity(userAndActivity);
                session.commit();
            }catch (Exception e){
                //出现异常，数据库回滚
                session.rollback();
                e.printStackTrace();
                return 1;
            }finally {
                MyBatisUtil.closeSession();
            }
            return 0;
        }else{
            MyBatisUtil.closeSession();
            //不是当前用户创建的活动
            return 1;
        }
    }

    @Override
    public List<Activity> getActivityByUser(String username) {
        SqlSession session = MyBatisUtil.getSession();
        ActivityDao activityDao = session.getMapper(ActivityDao.class);
        UserAndActivityDao userAndActivityDao = session.getMapper(UserAndActivityDao.class);

        List<UserAndActivity> userAndActivities = userAndActivityDao.selectByUser(username);
        List<Activity> activities = new ArrayList<Activity>();
        for(UserAndActivity userAndActivity : userAndActivities){
            Activity activity = activityDao.selectByName(userAndActivity.getActivity());
            activities.add(activity);
        }
        MyBatisUtil.closeSession();

        return activities;
    }

    @Override
    public List<User> getUserByActivity(String activity) {
        SqlSession session = MyBatisUtil.getSession();
        UserDao userDao = session.getMapper(UserDao.class);
        UserAndActivityDao userAndActivityDao = session.getMapper(UserAndActivityDao.class);

        List<UserAndActivity> userAndActivities = userAndActivityDao.selectByActivity(activity);
        List<User> users = new ArrayList<User>();
        for(UserAndActivity userAndActivity : userAndActivities){
            User user = userDao.selectByName(userAndActivity.getUser());
            users.add(user);
        }
        MyBatisUtil.closeSession();

        return users;
    }
}
