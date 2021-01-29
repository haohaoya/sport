package com.sports.service.impl;

import com.sports.dao.*;
import com.sports.domain.*;
import com.sports.service.CategoryService;
import com.sports.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/**
 * 分类服务实现类
 * 方法注释见对应接口
 */
public class CategoryServiceImpl implements CategoryService {

    @Override
    public List<CategoryPlus> getAllCategory() {
        SqlSession session = MyBatisUtil.getSession();
        CategoryDao categoryDao = session.getMapper(CategoryDao.class);
        UserAndActivityDao userAndActivityDao = session.getMapper(UserAndActivityDao.class);
        ActivityDao activityDao = session.getMapper(ActivityDao.class);
        UserDao userDao = session.getMapper(UserDao.class);
        List<Category> categories = categoryDao.selectAll();
        List<CategoryPlus> categoryPluses = new ArrayList<>();
        for(Category category : categories){
            CategoryPlus categoryPlus = new CategoryPlus();
            categoryPlus.setId(category.getId());
            categoryPlus.setCreator(category.getCreator());
            categoryPlus.setName(category.getName());

            List<Activity> activities = activityDao.selectSomeByCategory(category.getName());
            HashMap<String,User> userHashMap = new HashMap<>();
            for(Activity activity : activities){
                List<UserAndActivity> userAndActivities = userAndActivityDao.selectByActivity(activity.getName());
                for(UserAndActivity userAndActivity : userAndActivities){
                    User user = userDao.selectByName(userAndActivity.getUser());
                    if(userHashMap.get(user.getUsername())==null){
                        //该用户没被查到过
                        userHashMap.put(user.getUsername(),user);
                    }
                    //userHashMap.putIfAbsent(user.getUsername(), user);//或者直接用这个方法
                }
            }

            List<User> users = new ArrayList<User>(userHashMap.values());
            categoryPlus.setActivityNum(activities.size()+"");
            categoryPlus.setUserNum(users.size()+"");
            categoryPluses.add(categoryPlus);
        }
        MyBatisUtil.closeSession();
        return categoryPluses;
    }

    @Override
    public int addCategory(String name,String creator) {

        SqlSession session = MyBatisUtil.getSession();
        CategoryDao categoryDao = session.getMapper(CategoryDao.class);
        Category category = categoryDao.selectByName(name);
        if(category==null){
            //名没有重复
            category = new Category();
            category.setName(name);
            category.setCreator(creator);
            try{
                categoryDao.insertCategory(category);
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
        //名重复
        MyBatisUtil.closeSession();
        return 1;
    }

    @Override
    public int deleteCategory(String username, String categoryName) {
        //判断权限
        SqlSession session = MyBatisUtil.getSession();
        CategoryDao categoryDao = session.getMapper(CategoryDao.class);
        ActivityDao activityDao = session.getMapper(ActivityDao.class);
        UserAndActivityDao userAndActivityDao = session.getMapper(UserAndActivityDao.class);
        DiscussDao discussDao = session.getMapper(DiscussDao.class);

        Category category = categoryDao.selectByName(categoryName);
        if(category!=null && category.getCreator().equals(username)){
            //是当前用户创建的活动
            try{
                //查到该分类下所有的活动记录，根据活动名删除对应的userAndActivity记录,评论记录
                List<Activity> activities = activityDao.selectSomeByCategory(categoryName);
                for(Activity activity : activities){
                    userAndActivityDao.deleteUserAndActivityByActivity(activity.getName());
                    discussDao.deleteDiscussByActivity(activity.getName());
                }
                //删除是分类的所有活动
                activityDao.deleteActivityByCategory(category.getName());
                //删除该分类
                categoryDao.deleteCategoryByName(categoryName);
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
    public List<User> getUserByCategory(String name) {
        SqlSession session = MyBatisUtil.getSession();
        UserDao userDao = session.getMapper(UserDao.class);
        UserAndActivityDao userAndActivityDao = session.getMapper(UserAndActivityDao.class);
        ActivityDao activitydao = session.getMapper(ActivityDao.class);
        //从activity 表中获取对应分类下的活动
        List<Activity> activities = activitydao.selectSomeByCategory(name);
        //遍历activities 找到所有用户，这里可能会有一个用户在俩个活动中，做判断，或重写equals方法使用treeMap
        HashMap<String,User> userHashMap = new HashMap<>();
        for(Activity activity : activities){
            List<UserAndActivity> userAndActivities = userAndActivityDao.selectByActivity(activity.getName());
            for(UserAndActivity userAndActivity : userAndActivities){
                User user = userDao.selectByName(userAndActivity.getUser());
                if(userHashMap.get(user.getUsername())==null){
                    //该用户没被查到过
                    userHashMap.put(user.getUsername(),user);
                }
                //userHashMap.putIfAbsent(user.getUsername(), user);//或者直接用这个方法
            }
        }
        List<User> users = new ArrayList<User>(userHashMap.values());
        MyBatisUtil.closeSession();
        return users;
    }
}
