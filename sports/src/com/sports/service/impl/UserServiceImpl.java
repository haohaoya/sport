package com.sports.service.impl;

import com.sports.dao.UserAndActivityDao;
import com.sports.dao.UserDao;
import com.sports.domain.User;
import com.sports.service.UserService;
import com.sports.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.List;
/**
 * 用户服务实现类
 * 方法注释见对应接口
 */
public class UserServiceImpl implements UserService {
    @Override
    public int register(String name, String pwd, String sex) {
        SqlSession session = MyBatisUtil.getSession();
        UserDao userDao = session.getMapper(UserDao.class);
        User user = userDao.selectByName(name);
        if(user==null){
            //用户名没有重复
            user = new User();
            user.setUsername(name);
            user.setPassword(pwd);
            user.setSex(sex);
            try{
                userDao.insertUser(user);
                session.commit();
            }catch (Exception e){
                //出现异常，数据库回滚
                session.rollback();
                e.printStackTrace();
            }finally {
                MyBatisUtil.closeSession();
            }
            return 0;
        }
        MyBatisUtil.closeSession();
        return 1;
    }

    @Override
    public User login(String name, String pwd) {
        SqlSession session = MyBatisUtil.getSession();
        UserDao userDao = session.getMapper(UserDao.class);
        User user = userDao.selectByName(name);
        if(user!=null){
            //有该用户
            if(pwd.equals(user.getPassword())) {
                //密码正确
                MyBatisUtil.closeSession();
                return user;
            }else{
                MyBatisUtil.closeSession();
                return null;
            }
        }
        //没有该用户
        MyBatisUtil.closeSession();
        return null;
    }

    @Override
    public List<User> gerAllUser() {
        SqlSession session = MyBatisUtil.getSession();
        UserDao userDao = session.getMapper(UserDao.class);
        List<User> users = userDao.selectAll();
        MyBatisUtil.closeSession();
        return users;
    }

    @Override
    public User selectOne(String name) {
        SqlSession session = MyBatisUtil.getSession();
        UserDao userDao = session.getMapper(UserDao.class);
        User user = userDao.selectByName(name);
        MyBatisUtil.closeSession();
        return user;
    }

    @Override
    public List<User> selectSome(String name) {
        String dimName = "%"+name+"%";
        SqlSession session = MyBatisUtil.getSession();
        UserDao userDao = session.getMapper(UserDao.class);
        List<User> users = userDao.selectSomeByName(dimName);
        MyBatisUtil.closeSession();
        return users;
    }

    @Override
    public void updateByUsername(String name, String pwd, String sex) {
        SqlSession session = MyBatisUtil.getSession();
        UserDao userDao = session.getMapper(UserDao.class);
        User user = new User();
        user.setUsername(name);
        user.setPassword(pwd);
        user.setSex(sex);
        try{
            userDao.updateByUsername(user);
            session.commit();
        }catch (Exception e){
            //出现异常，数据库回滚
            session.rollback();
            e.printStackTrace();
        }finally {
            MyBatisUtil.closeSession();
        }
    }

    @Override
    public int registerByPhone(String name, String phone, String pwd, String sex) {
        SqlSession session = MyBatisUtil.getSession();
        UserDao userDao = session.getMapper(UserDao.class);
        User user = userDao.selectByName(name);
        if(user==null){
            //用户名没有重复
            user = userDao.selectByPhone(phone);
            if(user==null) {
                //手机号没有重复
                user = new User();
                user.setUsername(name);
                user.setPassword(pwd);
                user.setSex(sex);
                user.setPhone(phone);
                try {
                    userDao.insertUserByPhone(user);
                    session.commit();
                } catch (Exception e) {
                    //出现异常，数据库回滚
                    session.rollback();
                    e.printStackTrace();
                } finally {
                    MyBatisUtil.closeSession();
                }
                return 0;
            }else{
                //手机号重复
                MyBatisUtil.closeSession();
                return 2;
            }
        }
        //用户名重复
        MyBatisUtil.closeSession();
        return 1;
    }

    @Override
    public User loginByPhone(String phone, String pwd) {
        SqlSession session = MyBatisUtil.getSession();
        UserDao userDao = session.getMapper(UserDao.class);
        User user = userDao.selectByPhone(phone);
        if(user!=null){
            //有该用户
            if(pwd.equals(user.getPassword())) {
                //密码正确
                MyBatisUtil.closeSession();
                return user;
            }else{
                //密码错误
                MyBatisUtil.closeSession();
                return null;
            }
        }
        //没有该用户
        MyBatisUtil.closeSession();
        return null;
    }

    @Override
    public int update(int id, String oldusername,String newusername, String newpassword, String newphone) {
        SqlSession session = MyBatisUtil.getSession();
        UserDao userDao = session.getMapper(UserDao.class);
        UserAndActivityDao userAndActivityDao = session.getMapper(UserAndActivityDao.class);
        User user = userDao.selectByName(newusername);
        if(user==null||user.getId()==id){
            //用户名没有重复
            user = userDao.selectByPhone(newphone);
            if(user==null||user.getId()==id) {
                //手机号没有重复
                user = new User();
                user.setId(id);
                user.setUsername(newusername);
                user.setPassword(newpassword);
                user.setPhone(newphone);
                try {
                    userDao.updateByPhone(user);
                    if(!oldusername.equals(newusername)){
                        HashMap<String,String> map = new HashMap<>();
                        map.put("oldusername",oldusername);
                        map.put("newusername",newusername);
                        userAndActivityDao.update(map);
                    }
                    session.commit();
                } catch (Exception e) {
                    //出现异常，数据库回滚
                    session.rollback();
                    e.printStackTrace();
                } finally {
                    MyBatisUtil.closeSession();
                }
                return 0;
            }else{
                //手机号重复
                MyBatisUtil.closeSession();
                return 2;
            }
        }
        //用户名重复
        MyBatisUtil.closeSession();
        return 1;
    }
}
