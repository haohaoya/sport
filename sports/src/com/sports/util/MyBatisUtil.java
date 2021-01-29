package com.sports.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * mybatis工具类
 */
public class MyBatisUtil {
    //factory实例化的过程是一个比较耗费性能的过程。
    //保证有且只有一个factory
    private static SqlSessionFactory factory;
    private static ThreadLocal<SqlSession> tl = new ThreadLocal<>();
    static{
        try{
            InputStream is = Resources.getResourceAsStream("mybatis.xml");
            factory = new SqlSessionFactoryBuilder().build(is);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * 获取sqlsession
     * @return
     */
    public static SqlSession getSession(){
        SqlSession session = tl.get();
        if(session==null){
            tl.set(factory.openSession());
        }
        return tl.get();
    }

    /**
     * 关闭sqlsession
     */
    public static void closeSession(){
        SqlSession session = tl.get();
        if(session!=null){
            session.close();
        }
        tl.set(null);
    }
}
