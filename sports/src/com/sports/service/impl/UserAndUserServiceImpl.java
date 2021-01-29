package com.sports.service.impl;

import com.sports.dao.DiscussDao;
import com.sports.dao.UserAndUserDao;
import com.sports.dao.UserDao;
import com.sports.domain.Discuss;
import com.sports.domain.User;
import com.sports.domain.UserAndUser;
import com.sports.service.UserAndUserService;
import com.sports.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.List;
/**
 * 用户和用户服务实现类
 * 方法注释见对应接口
 */
public class UserAndUserServiceImpl implements UserAndUserService {
    @Override
    public int addFriend(String name, String username) {
        SqlSession session = MyBatisUtil.getSession();
        UserAndUserDao userAndUserDao = session.getMapper(UserAndUserDao.class);
        UserAndUser userAndUser1 = new UserAndUser();
        UserAndUser userAndUser2 = new UserAndUser();
        userAndUser1.setUserone(name);
        userAndUser1.setUsertwo(username);
        userAndUser2.setUserone(username);
        userAndUser2.setUsertwo(name);
        try{
            userAndUserDao.insertUserAndUser(userAndUser1);
            userAndUserDao.insertUserAndUser(userAndUser2);
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

    @Override
    public List<User> getFriend(String username) {
        SqlSession session = MyBatisUtil.getSession();
        UserAndUserDao userAndUserDao = session.getMapper(UserAndUserDao.class);
        UserDao userDao = session.getMapper(UserDao.class);
        List<UserAndUser> userAndUsers = userAndUserDao.selectByUser(username);
        List<User> users = new ArrayList<>();
        for(UserAndUser userAndUser : userAndUsers){
            User user = userDao.selectByName(userAndUser.getUsertwo());
            users.add(user);
        }
        MyBatisUtil.closeSession();
        return users;
    }

    @Override
    public int deleteFriend(String name, String username) {

        SqlSession session = MyBatisUtil.getSession();
        UserAndUserDao userAndUserDao = session.getMapper(UserAndUserDao.class);
        UserAndUser userAndUser1 = new UserAndUser();
        UserAndUser userAndUser2 = new UserAndUser();
        userAndUser1.setUserone(name);
        userAndUser1.setUsertwo(username);
        userAndUser2.setUserone(username);
        userAndUser2.setUsertwo(name);
        try{
            userAndUserDao.deleteUserAndUser(userAndUser1);
            userAndUserDao.deleteUserAndUser(userAndUser2);
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
}
