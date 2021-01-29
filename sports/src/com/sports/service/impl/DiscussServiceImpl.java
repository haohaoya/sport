package com.sports.service.impl;

import com.sports.dao.ActivityDao;
import com.sports.dao.DiscussDao;
import com.sports.dao.UserAndActivityDao;
import com.sports.domain.Activity;
import com.sports.domain.Discuss;
import com.sports.domain.UserAndActivity;
import com.sports.service.DiscussService;
import com.sports.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
/**
 * 讨论服务实现类
 * 方法注释见对应接口
 */
public class DiscussServiceImpl implements DiscussService {
    @Override
    public int addDiscuss(String username, String information, String activity) {

        SqlSession session = MyBatisUtil.getSession();
        DiscussDao discussDao = session.getMapper(DiscussDao.class);

        Discuss discuss = new Discuss();
        discuss.setInformation(information);
        discuss.setCreator(username);
        discuss.setActivity(activity);
        try{
            discussDao.insertDiscuss(discuss);
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
    public List<Discuss> getDiscuss(String name) {
        SqlSession session = MyBatisUtil.getSession();
        DiscussDao discussDao = session.getMapper(DiscussDao.class);
        List<Discuss> discusses = discussDao.selectByActivity(name);
        MyBatisUtil.closeSession();
        return discusses;
    }
}
