package com.sports.dao;

import com.sports.domain.Discuss;
import com.sports.domain.UserAndUser;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 用户和用户关系表的交互接口
 */
public interface UserAndUserDao {

    /**
     * 添加一条用户和用户关系（好友）
     * @param userAndUser 用户和用户关系信息
     */
    @Insert("insert into useranduser values(null,#{userone},#{usertwo})")
    void insertUserAndUser(UserAndUser userAndUser);

    /**
     * 删除关系
     * @param userAndUser 存储要删除关系的信息对象
     */
    @Delete("delete from useranduser where userone=#{userone} and usertwo=#{usertwo}")
    void deleteUserAndUser(UserAndUser userAndUser);

    /**
     * 根据用户名查询好友列表
     * @param username 用户名
     * @return 好友关系列表
     */
    @Select("select * from useranduser where userone=#{0}")
    List<UserAndUser> selectByUser(String username);
}
